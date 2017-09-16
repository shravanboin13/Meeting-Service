package com.accion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection="booking")
public class Booking extends BaseDomain implements Serializable{
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    private String roomId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Booking(String roomId, int id, Date beginDate, Date endDate, boolean state, String bookedBy, String name) {
        this.roomId = roomId;
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.state = state;
        this.bookedBy = bookedBy;
        this.name = name;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    @Id
    private int id;
    private Date beginDate,endDate;
    private boolean state;
    private String bookedBy;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
