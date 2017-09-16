package com.accion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection="room")
public class Room  implements Serializable{
    @Id
    private String id;

    public Room(String name, String description, int capacity, boolean networkAvailability, boolean conference, boolean video, boolean internet) {
      //  this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.networkAvailability = networkAvailability;
        this.conference = conference;
        this.video = video;
        this.internet = internet;
    }

    private String name;
    private String description; //description
    private int capacity;

    public Room() {
    }

    public Room(String id, String name, String description, int capacity, boolean networkAvailability, boolean conference, boolean video, boolean internet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.networkAvailability = networkAvailability;

        this.conference = conference;
        this.video = video;
        this.internet = internet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isNetworkAvailability() {
        return networkAvailability;
    }

    public void setNetworkAvailability(boolean networkAvailability) {
        this.networkAvailability = networkAvailability;
    }

    public boolean isConference() {
        return conference;
    }

    public void setConference(boolean conference) {
        this.conference = conference;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    private boolean networkAvailability;
        private boolean conference;
    private boolean video;
    private boolean internet;

}
