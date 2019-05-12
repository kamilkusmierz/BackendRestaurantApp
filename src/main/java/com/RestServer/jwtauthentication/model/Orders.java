package com.RestServer.jwtauthentication.model;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String name;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String LastName;
	
	
	private Timestamp startDate;
	
	
	private Timestamp endDate;
	
	@ManyToOne()
	@JoinColumn(name="table_id")
	private Tables table;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "Order_food", 
	    	joinColumns = @JoinColumn(name = "Order_id"), 
	    	inverseJoinColumns = @JoinColumn(name = "food_id"))
	 private List<Food> foods;
	
	public Orders() {}
	
	
	public Orders(@NotBlank @Size(min = 3, max = 50) String name, @NotBlank @Size(min = 3, max = 50) String lastName,
			Timestamp startDate, Timestamp endDate, Tables table, List<Food> foods) {
		
		this.name = name;
		LastName = lastName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.table = table;
		this.foods = foods;
	}


	public Tables getTable() {
		return table;
	}
	public void setTable(Tables table) {
		this.table = table;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
}
