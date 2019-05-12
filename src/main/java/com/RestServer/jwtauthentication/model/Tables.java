package com.RestServer.jwtauthentication.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name = "Tables")
public class Tables {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "x_cord",  nullable = false)
    private int x;
	@Column(name = "y_cord",  nullable = false)
    private int y;
	
	@NotBlank
	private String name;
	
	
	@ManyToOne()
	@JoinColumn(name="owner_id")
	private Resteurant owner;
	
	
	 @OneToMany(mappedBy = "table", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	  private List<Orders> orders;
	
	
	public Tables() {}
	 public Tables(int x, int y, String name, Resteurant owner) {
		 this.x=x;
		 this.y=y;
		 this.name=name;
		 this.owner = owner;
		 this.orders = null;
	 }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Resteurant getOwner() {
		return owner;
	}

	public void setOwner(Resteurant owner) {
		this.owner = owner;
	}
    public String toString() {
		String s = this.x + " " + this.y;
    	return s;
    	
    }
    
	
}
