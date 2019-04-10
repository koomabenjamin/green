package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 17/10/2017.
 */

public class Profiles {
    public String subject, _content, sender, date, time;

    public Profiles(){

    }

    public Profiles(String msg_subject, String msg_content, String msg_sender, String msg_date, String msg_time) {

        this.sender = msg_sender;
        this.subject = msg_subject;
        this.date = msg_date;
        this._content = msg_content;
        this.time = msg_time;

    }
}
