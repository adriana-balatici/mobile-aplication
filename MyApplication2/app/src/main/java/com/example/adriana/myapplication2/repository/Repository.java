package com.example.adriana.myapplication2.repository;

import com.example.adriana.myapplication2.domain.Restaurant;
import com.example.adriana.myapplication2.exceptions.RestaurantNotFoundException;

import java.util.List;

/**
 * Created by Adriana on 11/25/2017.
 */

public interface Repository {
    void addRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
    void editRestaurant(Integer position, Restaurant announcement) throws RestaurantNotFoundException;
    void deleteRestaurant(Integer position) throws RestaurantNotFoundException;
    Restaurant getRestaurant(Integer position) throws RestaurantNotFoundException;
    List<Restaurant> getRestaurants();
}
