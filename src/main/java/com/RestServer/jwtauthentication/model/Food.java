package com.RestServer.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Food")
public class Food {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String foodName;
	
	@Column(nullable = false)
    private int price;

	public Food() {
		
	}
	
	
	public Food(Long id, @NotBlank @Size(min = 3, max = 50) String foodName, int price) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.price = price;
	}


	public Food(@NotBlank @Size(min = 3, max = 50) String foodName, int price) {
		
		this.foodName = foodName;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
