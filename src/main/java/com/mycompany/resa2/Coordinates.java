/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resa2;

/**
 *
 * @author bacvikto
 */
public class Coordinates 
{
    private String city;
    private double latitude;
    private double longitude;
    
    public Coordinates(String city, double latitude, double longitude)
    {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    
    public double getLongitude()
    {
        return longitude;
    }
}