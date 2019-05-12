package com.RestServer.jwtauthentication.message.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.RestServer.jwtauthentication.model.Food;

public class OrderForm {
	 @NotBlank
	    @Size(min=3, max = 60)
	    private String name;
	    @NotBlank
	    @Size(min = 3, max = 60)
	    private String lastName;
	    @NotBlank
	    @Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}")
	    private String startDate;
	    @NotBlank
	    @Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}")
	    private String endDate;
	    @NotBlank
	    @Size(min = 3, max = 60)
	    private String resteurantName;
	    @NotBlank
	    @Size(min = 3, max = 60)
	    private String tableName;
	    @NotNull
	    private List<Food> food;
		public String getName() {
			return name;
		}
		
		
		public String getResteurantName() {
			return resteurantName;
		}


		public void setResteurantName(String resteurantName) {
			this.resteurantName = resteurantName;
		}


		public String getTableName() {
			return tableName;
		}


		public void setTableName(String tableName) {
			this.tableName = tableName;
		}


		public List<Food> getFood() {
			return food;
		}

		public void setFood(List<Food> food) {
			this.food = food;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	    
	    
}
