/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luckcompanies.models;

/**
 *
 * @author johnkirksey
 */
public class Flight {
    private String origin = "";
    private String destination = "";

    public Flight(String orig, String dest)
    {
        origin = orig;
        destination = dest;
    }
    
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
}