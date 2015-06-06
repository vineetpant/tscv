package com.social.dial.modalhack;

/**
 * Created by Asad on 05-Jun-15.
 */
public class PoliceStation {
    String station_id;
    String sho_uid;
    String address;
    String contact_num;

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getSho_uid() {
        return sho_uid;
    }

    public void setSho_uid(String sho_uid) {
        this.sho_uid = sho_uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }
    /*CREATE TABLE police_station (
            station_id  ;
            sho_uid     ;
            address     ;
            contact_num TEXT
    )*/
}
