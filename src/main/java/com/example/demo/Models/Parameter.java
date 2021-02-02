package com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

public @Entity class Parameter
{
    @Id
    private String mykey;

    @NotNull
    private String value;

    private int version;


    private Date created_at;
    private Date updated_at;



    public Parameter()
    {
        this.version = 0;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public Parameter(String mykey ,String value )
    {
        this.mykey = mykey;
        this.value = value;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
        this.version = 0;
    }


    public void setMykey(String mykey) {
        this.mykey = mykey;
    }

    public String getMykey() {
        return mykey;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void update(String mykey , String value )
    {
        this.mykey = mykey;
        this.value = value;
        this.updated_at = new Date();
        this.updated_at.setTime(System.currentTimeMillis());
        this.version +=1;
    }

    @Override
    public String toString() {
        return String.format("key : %s - value : %s - version : %d",this.mykey,this.value,this.version);
    }

}
