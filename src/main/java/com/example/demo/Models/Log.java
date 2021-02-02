package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public @Entity class Log
{
    private  @Id @GeneratedValue int id;

    @NotNull
    private String on_table;

    @NotNull
    private String record_id;

    @NotNull
    private String operation;

    @ManyToOne
    @JoinColumn(name="appuser_id")
    private Appuser appuser;

    private Date created_at;

    public Log()
    {
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public Log(String on_table , String record_id , String operation , Appuser appuser)
    {
        this.appuser = appuser ;
        this.on_table = on_table ;
        this.operation = operation;
        this.record_id = record_id;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOn_table(String on_table) {
        this.on_table = on_table;
    }

    public String getOn_table() {
        return on_table;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setAppuser(Appuser appuser) {
        this.appuser = appuser;
    }

    public Appuser getAppuser() {
        return appuser;
    }

    public Date getCreated_at() {
        return created_at;
    }
}
