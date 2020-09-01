package com.example.entity;

import java.io.Serializable;

public class Citiy_id implements Serializable{
	private String Citiy;
	private int Citiyid;
	
	
	public Citiy_id() {
		
	}
	
	public Citiy_id(String Citiy,int Citiyid) {
		super();
		this.Citiy=Citiy;
		this.Citiyid=Citiyid;
	}
	
	public String getCitiy() {
		return Citiy;
	}
	public void setCitiy(String citiy) {
		Citiy = citiy;
	}
	public int getCitiyid() {
		return Citiyid;
	}
	public void setCitiyid(int citiyid) {
		Citiyid = citiyid;
	}

}
