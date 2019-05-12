package com.RestServer.jwtauthentication.order;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.RestServer.jwtauthentication.message.request.OrderForm;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Orders;
import com.RestServer.jwtauthentication.model.Resteurant;
import com.RestServer.jwtauthentication.model.Tables;
import com.RestServer.jwtauthentication.repository.OrderRepository;
import com.RestServer.jwtauthentication.repository.ResteurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderControlerImp implements OrderControler {
    @Autowired
    ResteurantRepository resteurantRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public String saveOrder(OrderForm orderForm) throws ParseException {
        Resteurant tmpResteurant = resteurantRepository.findByName(orderForm.getResteurantName()).get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date parsedDate = dateFormat.parse(orderForm.getStartDate());
        Timestamp startdate = new java.sql.Timestamp(parsedDate.getTime());
        parsedDate = dateFormat.parse(orderForm.getEndDate());
        Timestamp enddate = new java.sql.Timestamp(parsedDate.getTime());
        System.out.println(orderForm.getTableName());
        for (Tables t : tmpResteurant.getTables()) {

            if (t.getName().equals(orderForm.getTableName())) {
                System.out.println(t.getName());
                Orders order = new Orders(orderForm.getName(), orderForm.getLastName(), startdate, enddate, t,
                        orderForm.getFood());
                orderRepository.save(order);
                return "Order saved";
            }

        }
        return "Error";
    }
}
