package com.sahin.archiving_system.model;

import jakarta.persistence.*;

@Entity
public class Share extends BaseEntity{

    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;

    @ManyToOne
    private File file;
    private String message;

    public Share(User sender, User receiver, File file, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.file = file;
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
