package com.example.mapper;
import java.util.List;

import com.example.entity.*;


public interface HoneyGoodMapper {
	List<HoneyGood> selectAll();
	int addneworder(HoneyGood honeygood);
	List<HoneyGood> selectwjq(String Honey_setle);
	int deletorder(int tingsID);
	Integer selectPeasonNum();

}
