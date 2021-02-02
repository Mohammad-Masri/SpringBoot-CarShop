package com.example.demo.myUtile.Security;

public class JwtResponse
{
    private String token;

    public JwtResponse()
    {}

    public JwtResponse(String token)
    {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
