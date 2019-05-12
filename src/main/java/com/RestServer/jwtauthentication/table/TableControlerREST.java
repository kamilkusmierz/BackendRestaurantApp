package com.RestServer.jwtauthentication.table;

import java.text.ParseException;

import javax.validation.Valid;

import com.RestServer.jwtauthentication.message.request.CheckTable;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/table")
public class TableControlerREST{
    @Autowired
    TableControler tableControler;

	@PostMapping("/checktable")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> saveOrder(@Valid @RequestBody CheckTable checkForm) throws ParseException {
    try {
        switch(tableControler.saveOrder(checkForm)){
            case "Table reserved":
            return new ResponseEntity<>(new ResponseMessage("Stolik Zajety"), HttpStatus.BAD_REQUEST);
            case "Stolik Wolny":
            return new ResponseEntity<>(new ResponseMessage("Ok"), HttpStatus.ACCEPTED);
            default:
            return new ResponseEntity<>(new ResponseMessage("Error"), HttpStatus.BAD_REQUEST);
        }
    } catch (Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }
}