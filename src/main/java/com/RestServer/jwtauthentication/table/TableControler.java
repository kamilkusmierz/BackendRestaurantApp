package com.RestServer.jwtauthentication.table;

import java.text.ParseException;

import javax.validation.Valid;

import com.RestServer.jwtauthentication.message.request.CheckTable;

import org.springframework.web.bind.annotation.RequestBody;

public interface TableControler {
   public String saveOrder(@Valid @RequestBody CheckTable checkForm) throws ParseException;
}