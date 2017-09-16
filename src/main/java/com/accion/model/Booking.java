package com.accion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Document(collection="booking")
public class Booking implements Serializable{
    @Id
    private String id;
    private String start,end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventURL() {
        return eventURL;
    }

    public void setEventURL(String eventURL) {
        this.eventURL = eventURL;
    }

    private boolean state;
    private String bookedBy;
    private String title;
    private String location;
    private String roomId;

    public Booking(String id, String beginDate, String endDate, boolean state, String bookedBy, String summary, String location, String roomId, String eventURL, List<String> attendees) {
        this.id = id;
        this.start = beginDate;
        this.end = endDate;
        this.state = state;
        this.bookedBy = bookedBy;
        this.title = summary;
        this.location = location;
        this.roomId = roomId;
        this.eventURL = eventURL;
        this.attendees = attendees;
    }

    private String eventURL;
    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;

    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    private List<String> attendees;








    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }





    public boolean isState() {
        return state;
    }

    public Booking() {
    }

    public void setState(boolean state) {
        this.state = state;
    }



    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }


   }
