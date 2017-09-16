package com.accion.utils;

/**
 * Created by AL1775 on 03-07-2017.
 */
public class Message {

    public static final String SUCCESS_MESSAGE  = "success";
    public static final String ERROR_MESSAGE    = "failure";

    private String messageType;
    private String message;

    public Message(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
    }
}