package com.RestServer.jwtauthentication.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CheckTable {
    @NotBlank
    @Size(min = 1, max = 25)
	private String resteurantName;
    @NotBlank
    @Size(min = 1, max = 25)
	private String tableName;
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}")
	private String data;
    @NotBlank
    @Pattern(regexp="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}")
    @Size(min = 3, max = 50)
	private String data2;

	public CheckTable(String resteurantName, String tableName, String data, String data2) {
		
		this.resteurantName = resteurantName;
		this.tableName = tableName;
		this.data = data;
		this.data2 = data2;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
