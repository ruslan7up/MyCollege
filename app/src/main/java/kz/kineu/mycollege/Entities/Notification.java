package kz.kineu.mycollege.Entities;

import java.util.Date;

/**
 * Created by ruslan on 16.02.2017.
 */

public class Notification {

    private Long id;
    private String notifymessage;
    private Date date;

    public Notification() {
    }

    public Notification(Long id, String notifymessage, Date date) {
        this.id = id;
        this.notifymessage = notifymessage;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotifymessage() {
        return notifymessage;
    }

    public void setNotifymessage(String notifymessage) {
        this.notifymessage = notifymessage;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
