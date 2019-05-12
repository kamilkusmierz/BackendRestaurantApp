package com.RestServer.jwtauthentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.RestServer.jwtauthentication.model.Tables;


@Repository
public interface TablesRepository extends JpaRepository<Tables, Long> {
    Optional<Tables> findById(Long id);
    
   
}