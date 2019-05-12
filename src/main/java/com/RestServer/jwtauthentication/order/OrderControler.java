package com.RestServer.jwtauthentication.order;

import java.text.ParseException;

import com.RestServer.jwtauthentication.message.request.OrderForm;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;

public interface OrderControler {
    public String saveOrder(OrderForm orderForm) throws ParseException;
}