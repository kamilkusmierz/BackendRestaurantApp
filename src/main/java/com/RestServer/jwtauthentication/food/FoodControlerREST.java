package com.RestServer.jwtauthentication.food;

import java.util.List;

import javax.validation.Valid;

import com.RestServer.jwtauthentication.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodControlerREST {
    @Autowired
    FoodControler foodControler;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getfood")
    public List<Food> getFood() {
        return foodControler.getFood();
    }

    @PostMapping("/addfood")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveFood(@Valid @RequestBody Food food) {
        return new ResponseEntity<>(foodControler.saveFood(food), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletefood")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAllFoods() {

        return new ResponseEntity<>(foodControler.deleteAllFoods(), HttpStatus.OK);
    }

    @DeleteMapping("/deletefood/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFood(@PathVariable("id") long id) {

        return new ResponseEntity<>(foodControler.deleteFood(id), HttpStatus.OK);
    }

}