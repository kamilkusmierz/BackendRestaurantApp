package com.RestServer.jwtauthentication.order;

import java.text.ParseException;

import javax.validation.Valid;

import com.RestServer.jwtauthentication.message.request.OrderForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderControlerREST {
    @Autowired
    OrderControler orderControler;

    @PostMapping("/saveorder")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderForm orderForm) throws ParseException {
        try {
            switch (orderControler.saveOrder(orderForm)) {
            case "Order saved":
                return new ResponseEntity<>("Order saved", HttpStatus.OK);
            default:
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        

    }
}