package com.RestServer.jwtauthentication.message.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class CreateResteurantForm {
	@NotBlank
	private MultipartFile file;
	@NotBlank
    @Size(min = 1, max = 26)
	@Pattern(regexp="^[A-Za-z]{1,25}$")
    private String resteurantname;

    @NotBlank
    @Size(min = 1, max = 26)
    @Pattern(regexp="^[A-Za-z]{1,25}$")
    private String streetNumber;

    @NotBlank
    @Size(max = 4)
    @Pattern(regexp="^[0-9-/]{1,4}$")
    private String houseNumber;
    
    @NotBlank
    @Size(max = 6)
    @Pattern(regexp="[0-9]{2}-[0-9]{3}")
    private String code;
    
    @NotBlank
    @Size(max = 60)
    @Pattern(regexp="^[A-Za-z]{1,25}$")
    private String cityName;
    @NotBlank
    @Size(min = 1, max= 50)
    private String tables;
    
    
    public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public String getResteurantname() {
		return resteurantname;
	}
	public void setResteurantname(String resteurantname) {
		this.resteurantname = resteurantname;
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
	
	
}
