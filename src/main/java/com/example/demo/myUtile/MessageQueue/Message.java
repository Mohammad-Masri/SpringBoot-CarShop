package com.example.demo.myUtile.MessageQueue;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
    private String username
            , content ;

    private Date date;


    public Message(String username , String content)
    {
        this.content = content;
        this.username = username;
        this.date = new Date();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return String.format("content : %s - from : %s ",this.content,this.username) +"- in : "+ date;
    }
}
