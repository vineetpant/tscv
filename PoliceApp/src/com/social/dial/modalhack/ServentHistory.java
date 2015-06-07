package com.social.dial.modalhack;

/**
 * Created by Asad on 06-Jun-15.
 */
public class ServentHistory {

    String servant_id;
    String employer_uid;
    String start_date;
    String end_date;
    String verification_status_police;

    public String getServant_id() {
        return servant_id;
    }

    public void setServant_id(String servant_id) {
        this.servant_id = servant_id;
    }

    public String getEmployer_uid() {
        return employer_uid;
    }

    public void setEmployer_uid(String employer_uid) {
        this.employer_uid = employer_uid;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getVerification_status_police() {
        return verification_status_police;
    }

    public void setVerification_status_police(String verification_status_police) {
        this.verification_status_police = verification_status_police;
    }
}
