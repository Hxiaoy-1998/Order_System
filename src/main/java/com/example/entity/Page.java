package com.example.entity;

public class Page {
	private int pagesum;
	private int pagenum;

	public Page() {
		
	}
	
	public Page(int pagesum,int pagenum) {
		this.pagesum = pagesum;
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
}
