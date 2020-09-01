package com.example.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.ser.Serializers;

public class HoneyGood implements Serializable,Comparable<HoneyGood>{
	private int ThingsID;
	private int Honey_weight;
	private int Honey_price;
	private String Honey_buyer;
	private String Honey_buy_time;
	private String Honey_buyer_area;
	private String Honey_buyer_secarea;
	private String Honey_setle;
	/* 
	 * <th>订单编号</th>
      <th>买家姓名</th>
      <th>下单时间</th>
      <th>下单地点</th>
      <th>下单数量</th>
      <th>成交金额</th>
      <th>成交状态</th>
	 * */
	
	public HoneyGood() {
		
	}
	
	public HoneyGood(int ThingsID,int Honey_weight,int Honey_price
			,String Honey_buyer,String Honey_buy_time,String Honey_buyer_area,
			String Honey_setle)
	{
		
		super();
		this.ThingsID=ThingsID;
		this.Honey_weight=Honey_weight;
		this.Honey_price=Honey_price;
		this.Honey_buyer=Honey_buyer;
		this.Honey_buy_time=Honey_buy_time;
		this.Honey_buyer_area=Honey_buyer_area;
		this.Honey_setle=Honey_setle;
	}

	public int getThingsID() {
		return ThingsID;
	}

	public void setThingsID(int thingsID) {
		ThingsID = thingsID;
	}

	public int getHoney_weight() {
		return Honey_weight;
	}

	public void setHoney_weight(int honey_weight) {
		Honey_weight = honey_weight;
	}

	public int getHoney_price() {
		return Honey_price;
	}

	public void setHoney_price(int honey_price) {
		Honey_price = honey_price;
	}

	public String getHoney_buyer() {
		return Honey_buyer;
	}

	public void setHoney_buyer(String honey_buyer) {
		Honey_buyer = honey_buyer;
	}

	public String getHoney_buy_time() {
		return Honey_buy_time;
	}

	public void setHoney_buy_time(String honey_buy_time) {
		Honey_buy_time = honey_buy_time;
	}

	public String getHoney_buyer_area() {
		return Honey_buyer_area;
	}

	public void setHoney_buyer_area(String honey_buyer_area) {
		Honey_buyer_area = honey_buyer_area;
	}

	public String getHoney_setle() {
		return Honey_setle;
	}

	public void setHoney_setle(String honey_setle) {
		Honey_setle = honey_setle;
	}
	
	
	public String delDate(Date Honey_buy_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String buytimestr = sdf.format(Honey_buy_time);
		
		return buytimestr;
	}

	@Override
	public String toString() {
		return "HoneyGood [ThingsID=" + ThingsID + ", Honey_weight=" + Honey_weight + ", Honey_price=" + Honey_price
				+ ", Honey_buyer=" + Honey_buyer + ", Honey_buy_time=" + Honey_buy_time + ", Honey_buyer_area="
				+ Honey_buyer_area +"Honey_buyer_secarea,"+Honey_buyer_secarea+ ", Honey_setle=" + Honey_setle + "]";
	}

	public String getHoney_buyer_secarea() {
		return Honey_buyer_secarea;
	}

	public void setHoney_buyer_secarea(String honey_buyer_secarea) {
		Honey_buyer_secarea = honey_buyer_secarea;
	}

	@Override
	public int compareTo(HoneyGood honeygood) {
		return this.ThingsID-honeygood.getThingsID();
		
	}

//	@Override
//	public String toString() {
//		return "订单编号：" + ThingsID + "\n"+"售出重量：" + Honey_weight +"斤"+ "\n"+"售价：" + Honey_price
//				+"\n" +"购买人:" + Honey_buyer + "\n"+"购买时间:" + Honey_buy_time + "\n"+"下单地点："
//				+ Honey_buyer_area + "\n"+"是否结算：" + Honey_setle ;
//	}
//	

}
