package com.example.mapper;

import java.util.List;

import com.example.entity.*;

public interface CitiyAndPeasonMapper {
	int adpeaToCitiy(String  son_city, String Citiy,int peason_num);
	Integer selectCitiy(String Citiy,String son_city);
	int getnewScitiy(String  son_city,int to_city_id,int peason_num);
	
}
