package com.example.adriana.myapplication2.exceptions;

/**
 * Created by Adriana on 11/25/2017.
 */

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
