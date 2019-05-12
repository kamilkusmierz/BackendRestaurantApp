package com.RestServer.jwtauthentication.table;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.RestServer.jwtauthentication.message.request.CheckTable;
import com.RestServer.jwtauthentication.model.Orders;
import com.RestServer.jwtauthentication.model.Resteurant;
import com.RestServer.jwtauthentication.model.Tables;
import com.RestServer.jwtauthentication.repository.ResteurantRepository;
import com.RestServer.jwtauthentication.repository.TablesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class TableControlerImp implements TableControler {
    @Autowired
    TablesRepository tablesRepository;
    @Autowired
    ResteurantRepository resteurantRepository;
    @Override
    public String saveOrder(@Valid CheckTable checkForm) throws ParseException {
        
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		Date parsedDate = dateFormat.parse(checkForm.getData());
		Timestamp startdate = new java.sql.Timestamp(parsedDate.getTime());
		parsedDate = dateFormat.parse(checkForm.getData2());
		Timestamp enddate = new java.sql.Timestamp(parsedDate.getTime());
		Resteurant resteurant = resteurantRepository.findByName(checkForm.getResteurantName()).get(0);
		
		for (Tables t : resteurant.getTables()) {
			if (t.getName().equals(checkForm.getTableName())) {
				System.out.println(t.getName());
				List<Orders> OrderList = t.getOrders();
				for (Orders o : OrderList) {
					
					Timestamp fromtime = o.getStartDate();
					
					Timestamp totime = o.getEndDate();
					System.out.println(fromtime);
					System.out.println(totime);
					System.out.println(startdate);
					System.out.println(enddate);
					if ((fromtime.after(startdate) && totime.before(startdate)) || (fromtime.after(enddate) && totime.before(enddate))) {
					//	return new ResponseEntity<>(new ResponseMessage("Stolik Zajety"), HttpStatus.BAD_REQUEST);
                        return "Table reserved";
					} 
				}
			}

		}

	//	return new ResponseEntity<>(new ResponseMessage("Ok"), HttpStatus.ACCEPTED);
	return "Stolik Wolny";
    }
    
}