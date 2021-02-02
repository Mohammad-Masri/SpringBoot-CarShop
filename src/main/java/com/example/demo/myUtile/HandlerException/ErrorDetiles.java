package com.example.demo.myUtile.HandlerException;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorDetiles {

    private String message;

    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy hh:mm:ss")
    private Date time;

    public ErrorDetiles ()
    {
        this.time = new Date();
    }

    public ErrorDetiles (String message , String url )
    {
        this();
        this.message = message;
        this.url = url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public Date getTime() {
        return time;
    }
}
