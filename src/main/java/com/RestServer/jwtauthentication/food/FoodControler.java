package com.RestServer.jwtauthentication.food;

import java.util.List;

import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Food;

public interface FoodControler {
    public List<Food> getFood();

    public ResponseMessage saveFood(Food food);

    public ResponseMessage deleteAllFoods();

    public ResponseMessage deleteFood(long id);
}