package com.accion.api;


import com.accion.model.Booking;
import com.accion.model.Room;
import com.accion.services.MeetingService;
import com.accion.utils.CommonUtils;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * This class handles rest calls for meetings services
 * @version
 * @author AL1867
 *@since 12-06-2017
 */
@Api(value = "Meeting", description = "Meeting desc")
@CrossOrigin
@RestController
@RequestMapping(value="/meeting/v1/meeting-service")
public class MeetingServiceController {

    @Autowired
    MeetingService roomService;
    @Autowired
    CommonUtils commonUtils;

    @Autowired
    private HttpSession httpSession;

    private final static Log logger = LogFactory.getLog(MeetingServiceController.class);
    private static final String APPLICATION_NAME = "";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static Calendar client;

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;

    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;


    /**
     * This method is used to save rooms into database.
     * This method accepts room object as input and stores the room element object into database
     *
     * @return this method returns room object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> saveRooms(@RequestBody Room room) throws Exception {
        Room roomResult = roomService.saveRooms(room);
        return new ResponseEntity<>(roomResult, HttpStatus.OK);

    }

    /**
     * This method is used to fetch room elements from database.

     * @return this method returns list of room object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Room>> getRooms(@RequestParam Map queryMap) throws Exception {
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Room> rooms = roomService.getAllRooms(page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Room>>(rooms, HttpStatus.OK);

    }

    /**
     * This method is used to search rooms from database.
     * This method accepts last name as input and fetches the library element details from database
     *
     * @return this method returns list of room objects
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Room>> searchRooms(@RequestParam Map queryMap) throws Exception {
        String name = (String) queryMap.get("name");
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Room> libraryElements = roomService.findByRoomPartialName(name, page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Room>>(libraryElements, HttpStatus.OK);

    }

    /**
     * This method is used to find room by ID from database.
     * This method accepts ID as input and fetches the room details from database
     *
     * @return this method returns room object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") final String id) throws Exception {
        Room room = roomService.findOneRoom(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    /**
     * This method is used to update rooms .
     * This method accepts room as input and based on id updates the record in the database
     *
     * @return this method returns room
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) throws Exception {
        room = roomService.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }


    /**
     * This method is used to save bookings into database.
     * This method accepts booking object as input and stores the booking object into database
     *
     * Note : This has to authenticated
     * @return this method returns booking object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> saveBookings(@RequestBody Booking room) throws Exception {
        Booking bookingResult = roomService.saveBookings(room);
//        Google API starts.
        client = (Calendar) httpSession.getAttribute("client");
        if(client != null) {
            Event event = new Event()
                    .setSummary(bookingResult.getTitle())
                    .setLocation("WhiteField")
                    .setDescription(bookingResult.getTitle());

            DateTime startDateTime = new DateTime(bookingResult.getStart());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("Asia/Kolkata");
            event.setStart(start);

            DateTime endDateTime = new DateTime(bookingResult.getEnd());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("Asia/Kolkata");
            event.setEnd(end);

            String[] recurrence = new String[]{"RRULE:FREQ=DAILY;COUNT=2"};
            event.setRecurrence(Arrays.asList(recurrence));

            List<EventAttendee> attendees = bookingResult.getAttendees().stream().map(s -> new EventAttendee().setEmail(s)).collect(Collectors.toList());
            event.setAttendees(attendees);

            EventReminder[] reminderOverrides = new EventReminder[]{
                    new EventReminder().setMethod("email").setMinutes(24 * 60),
                    new EventReminder().setMethod("popup").setMinutes(10),
            };
            Event.Reminders reminders = new Event.Reminders()
                    .setUseDefault(false)
                    .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);

            String calendarId = "primary";
            event = client.events().insert(calendarId, event).execute();
            System.out.printf("Event created: %s\n", event.getHtmlLink());
        }
//        Google API ends.
        return new ResponseEntity<>(bookingResult, HttpStatus.OK);

    }

    /**
     * This method is used to fetch bookings from database.
     * @return this method returns list of booking object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Booking>> getAllBookings(@RequestParam Map queryMap) throws Exception {
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Booking> bookings = roomService.getAllBookings(page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Booking>>(bookings, HttpStatus.OK);

    }

    /**
     * This method is used to search bookings from database.
     * This method accepts  name as input and fetches the booking details from database
     *
     * @return this method returns list of booking objects
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Booking>> searchBookings(@RequestParam Map queryMap) throws Exception {
        String name = (String) queryMap.get("name");
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Booking> bookings = roomService.findByBookingPartialName(name, page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Booking>>(bookings, HttpStatus.OK);

    }

    /**
     * This method is used to find bookings by ID from database.
     * This method accepts ID as input and fetches the booking  details from database
     *
     * @return this method returns booking object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBooking(@PathVariable("id") final String id) throws Exception {
        Booking booking = roomService.findOneBooking(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * This method is used to update booking .
     * This method accepts booking object as input and based on id updates the record in the database
     *
     * @return this method returns booking
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) throws Exception {
        booking = roomService.updateBooking(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
        return new RedirectView(authorize());
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public ResponseEntity<String> oauth2Callback(@RequestParam(value = "code") String code) {
        com.google.api.services.calendar.model.Events eventList;
        String message="success";
        try {
            TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
            credential = flow.createAndStoreCredential(response, "userID");
            client = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            httpSession.setAttribute("client",client);
        } catch (Exception e) {
            logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.");
            message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.";
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton(CalendarScopes.CALENDAR)).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
        System.out.println("cal authorizationUrl->" + authorizationUrl);
        return authorizationUrl.build();
    }

}
