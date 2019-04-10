package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 28/11/2017.
 */

public class Message {
    private String subject;
    private String content;
    private String sender;
    private String type;
    private String receivedDate;
    private String timeStamp;

    public Message() {
    }

    public Message(String subject, String content, String sender, String type, String receivedDate, String timeStamp) {
        this.subject = subject;
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.receivedDate = receivedDate;
        this.timeStamp = timeStamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
