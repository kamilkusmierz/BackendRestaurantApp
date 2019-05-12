package com.RestServer.jwtauthentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RestServer.jwtauthentication.model.Food;




@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	Optional<Food> findById(Long id);

}