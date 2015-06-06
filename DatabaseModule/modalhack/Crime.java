package com.social.dial.modalhack;

/**
 * Created by Asad on 05-Jun-15.
 */
public class Crime {
    String uid;
    String date;
    String case_status;
    String description;
    String jail_term;
    String police_station_id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCase_status() {
        return case_status;
    }

    public void setCase_status(String case_status) {
        this.case_status = case_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJail_term() {
        return jail_term;
    }

    public void setJail_term(String jail_term) {
        this.jail_term = jail_term;
    }

    public String getPolice_station_id() {
        return police_station_id;
    }

    public void setPolice_station_id(String police_station_id) {
        this.police_station_id = police_station_id;
    }
}
