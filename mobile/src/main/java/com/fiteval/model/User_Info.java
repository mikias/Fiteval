package com.fiteval.model;

/**
 * Created by mikias on 11/14/16.
 */

public class User_Info {
    private String u_id;
    private String u_first_name;
    private String u_last_name;
    private String u_email;

    public User_Info(String u_id, String u_first_name, String u_last_name, String u_email) {
        this.u_email = u_email;
        this.u_first_name = u_first_name;
        this.u_id = u_id;
        this.u_last_name = u_last_name;
    }

    public String getU_last_name() {
        return u_last_name;
    }

    public void setU_last_name(String u_last_name) {
        this.u_last_name = u_last_name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_first_name() {
        return u_first_name;
    }

    public void setU_first_name(String u_first_name) {
        this.u_first_name = u_first_name;
    }



}
