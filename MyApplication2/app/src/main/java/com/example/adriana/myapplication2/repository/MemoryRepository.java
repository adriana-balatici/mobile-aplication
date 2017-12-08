package com.example.adriana.myapplication2.repository;

import com.example.adriana.myapplication2.domain.Restaurant;
import com.example.adriana.myapplication2.exceptions.RestaurantNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adriana on 11/25/2017.
 */

public class MemoryRepository implements Repository {
    private List<Restaurant> restaurants;

    public MemoryRepository() {
        this.restaurants = new ArrayList<>();
        this.restaurants.add(new Restaurant( "That thing is done", "aaaa", 3));
        this.restaurants.add(new Restaurant( "That other thing is done", "aaaa", 5));
        this.restaurants.add(new Restaurant( "3 done for today", "vvvvv", 4));

    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {

        this.restaurants.add(restaurant);
    }

    @Override
    public void editRestaurant(Integer position, Restaurant restaurant) {
        this.restaurants.set(position, restaurant);
    }
    @Override
    public void deleteRestaurant(Integer position) throws RestaurantNotFoundException{
        if (position >= this.restaurants.size() || position < 0)
        {
            throw new RestaurantNotFoundException("Restaurants was not found!");
        }
        this.restaurants.remove(position);
    }
    @Override
    public Restaurant getRestaurant(Integer position) throws RestaurantNotFoundException {
        if (position >= this.restaurants.size() || position < 0)
        {
            throw new RestaurantNotFoundException("Restaurant was not found!");
        }
        return this.restaurants.get(position);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }
}