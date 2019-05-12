package com.RestServer.jwtauthentication.food;

import java.util.ArrayList;
import java.util.List;

import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Food;
import com.RestServer.jwtauthentication.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodControlerImp implements FoodControler {
    @Autowired
    FoodRepository foodRepository;

    @Override
    public List<Food> getFood() {
        List<Food> food = new ArrayList<>();
        foodRepository.findAll().forEach(food::add);
        return food;
    }

    @Override
    public ResponseMessage saveFood(Food food) {
        foodRepository.save(new Food(food.getFoodName(), food.getPrice()));
        return new ResponseMessage("Food added to menu");
    }

    @Override
    public ResponseMessage deleteAllFoods() {
        foodRepository.deleteAll();
        return new ResponseMessage("All Food deleted from menu");
    }

    @Override
    public ResponseMessage deleteFood(long id) {
        foodRepository.deleteById(id);
        return new ResponseMessage("Food deleted");
    }

}