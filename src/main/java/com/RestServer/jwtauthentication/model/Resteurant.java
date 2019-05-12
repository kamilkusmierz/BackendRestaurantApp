package com.RestServer.jwtauthentication.model;
import com.RestServer.jwtauthentication.model.Tables;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "Resteurant")
public class Resteurant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @NotBlank
	    @Size(min=3, max = 50)
	    private String name;
	 @NotBlank
	    @Size(min=3, max = 50)
	    private String filename;
	  @NotBlank
	    @Size(min = 3, max = 50)
	    private String streetNumber;

	    @NotBlank
	    @Size(max = 60)
	    private String houseNumber;
	    
	    @NotBlank
	    @Size(max = 6)
	    private String code;
	    
	    @NotBlank
	    @Size(max = 60)
	    private String cityName;
	   
	 
	 @OneToMany(mappedBy = "owner", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	  private List<Tables> tables;
	
	 public Resteurant() {}


		public Resteurant(@NotBlank @Size(min = 3, max = 50) String name,
				@NotBlank @Size(min = 3, max = 50) String filename,
				@NotBlank @Size(min = 3, max = 50) String streetNumber, @NotBlank @Size(max = 60) String houseNumber,
				@NotBlank @Size(max = 6) String code, @NotBlank @Size(max = 60) String cityName) {
			
			
			this.name = name;
			this.filename = filename;
			this.streetNumber = streetNumber;
			this.houseNumber = houseNumber;
			this.code = code;
			this.cityName = cityName;

		}

		public String getStreetNumber() {
			return streetNumber;
		}


		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}


		public String getHouseNumber() {
			return houseNumber;
		}


		public void setHouseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
		}


		public String getCode() {
			return code;
		}


		public void setCode(String code) {
			this.code = code;
		}


		public String getCityName() {
			return cityName;
		}


		public void setCityName(String cityName) {
			this.cityName = cityName;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public List<Tables> getTables() {
			return tables;
		}

		public void setTables(List<Tables> tables) {
			this.tables = tables;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
	    
}
