package com.accion.api;


import com.accion.model.Booking;
import com.accion.model.Room;
import com.accion.services.MeetingService;
import com.accion.utils.CommonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * This class handles rest calls for template services
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




    /**
     * This method is used to save library elements into database.
     * This method accepts library element object as input and stores the library element object into database
     *
     * @return this method returns custom element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> saveCustomElements(@RequestBody Room room) throws Exception {
        Room roomResult = roomService.saveRooms(room);
        return new ResponseEntity<>(roomResult, HttpStatus.OK);

    }

    /**
     * This method is used to fetch library elements from database.
        * This method accepts elementType(ex:custom ,hierarchy) as input and returns the library element object
     *
     * @return this method returns list of custom element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Room>> getCustomElements(@RequestParam Map queryMap) throws Exception {
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Room> rooms = roomService.getAllRooms(page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Room>>(rooms, HttpStatus.OK);

    }

    /**
     * This method is used to search library elements from database.
     * This method accepts last name,elementType(ex:custom ,hierarchy) as input and fetches the library element details from database
     *
     * @return this method returns list of custom element object
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
     * This method is used to find library elements by ID from database.
     * This method accepts ID as input and fetches the library element details from database
     *
     * @return this method returns library element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rooms/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") final String id) throws Exception {
        Room room = roomService.findOneRoom(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    /**
     * This method is used to update library elements .
     * This method accepts library elements as input and based on id updates the record in the database
     *
     * @return this method returns library element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> updateRoomo(@RequestBody Room room) throws Exception {
        room = roomService.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }


    /**
     * This method is used to save library elements into database.
     * This method accepts library element object as input and stores the library element object into database
     *
     * @return this method returns custom element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> saveCustomElements(@RequestBody Booking room) throws Exception {
        Booking roomResult = roomService.saveBookings(room);
        return new ResponseEntity<>(roomResult, HttpStatus.OK);

    }

    /**
     * This method is used to fetch library elements from database.
     * This method accepts elementType(ex:custom ,hierarchy) as input and returns the library element object
     *
     * @return this method returns list of custom element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Booking>> getAllBookings(@RequestParam Map queryMap) throws Exception {
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Booking> rooms = roomService.getAllBookings(page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Booking>>(rooms, HttpStatus.OK);

    }

    /**
     * This method is used to search library elements from database.
     * This method accepts last name,elementType(ex:custom ,hierarchy) as input and fetches the library element details from database
     *
     * @return this method returns list of custom element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Booking>> searchBookings(@RequestParam Map queryMap) throws Exception {
        String name = (String) queryMap.get("name");
        int page = Integer.parseInt((String) queryMap.get("page"));
        int size = Integer.parseInt((String) queryMap.get("size"));
        String sortBy = (String) queryMap.get("sortBy");
        String sortOrder = (String) queryMap.get("sortOrder");
        Page<Booking> libraryElements = roomService.findByBookingPartialName(name, page, size, sortBy, sortOrder);
        return new ResponseEntity<Page<Booking>>(libraryElements, HttpStatus.OK);

    }

    /**
     * This method is used to find library elements by ID from database.
     * This method accepts ID as input and fetches the library element details from database
     *
     * @return this method returns library element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/bookings/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBooking(@PathVariable("id") final String id) throws Exception {
        Booking room = roomService.findOneBooking(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    /**
     * This method is used to update library elements .
     * This method accepts library elements as input and based on id updates the record in the database
     *
     * @return this method returns library element object
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> updateRoomo(@RequestBody Booking room) throws Exception {
        room = roomService.updateBooking(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }


}
