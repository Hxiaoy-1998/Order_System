package com.example.entity;

public class ProvincePeasonNum {
	private String Citiy;
	private int peason_num;
	
	
	public ProvincePeasonNum() {}
	public ProvincePeasonNum(String Citiy,int peason_num) {
		this.Citiy=Citiy;
		this.peason_num=peason_num;
	}
	public String getCitiy() {
		return Citiy;
	}
	public void setCitiy(String citiy) {
		Citiy = citiy;
	}
	public int getPeason_num() {
		return peason_num;
	}
	public void setPeason_num(int peason_num) {
		this.peason_num = peason_num;
	}

}
