package com.RestServer.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RestServer.jwtauthentication.model.Orders;



@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	List<Orders> findByName(String name);

}