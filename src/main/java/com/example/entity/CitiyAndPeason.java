package com.example.entity;

import java.io.Serializable;

import ch.qos.logback.core.joran.spi.NoAutoStart;


public class CitiyAndPeason implements Serializable{
	private String son_city;
	private int peason;
	private int to_city_id;
	
	public CitiyAndPeason() {
		
	}
	
	public CitiyAndPeason(String son_city,int peason,int to_city_id) {
		super();
		this.peason=peason;
		this.son_city=son_city;
		this.to_city_id=to_city_id;
	}
	
	
	
	public String getSon_city() {
		return son_city;
	}
	public void setSon_city(String son_city) {
		this.son_city = son_city;
	}
	public int getPeason() {
		return peason;
	}
	public void setPeason(int peason) {
		this.peason = peason;
	}
	public int getTo_city_id() {
		return to_city_id;
	}
	public void setTo_city_id(int to_city_id) {
		this.to_city_id = to_city_id;
	}
	
	
}
