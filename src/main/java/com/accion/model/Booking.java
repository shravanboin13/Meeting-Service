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
    private Date beginDate,endDate;
    private boolean state;
    private String bookedBy;
    private String summary;
    private String location;
    private String roomId;

    public Booking(String id, Date beginDate, Date endDate, boolean state, String bookedBy, String summary, String location, String roomId, String eventURL, List<String> attendees) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.state = state;
        this.bookedBy = bookedBy;
        this.summary = summary;
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



    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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




    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
