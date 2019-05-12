package com.RestServer.jwtauthentication.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RestServer.jwtauthentication.model.Resteurant;


@Repository
public interface ResteurantRepository extends JpaRepository<Resteurant, Long> {
	List<Resteurant> findByName(String name);
    
    Boolean existsByName(String name);
}