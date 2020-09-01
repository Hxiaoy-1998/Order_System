package com.example.demo;


import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.mapper.CitiyAndPeasonMapper;
import com.example.mapper.Citiy_idMapper;
import com.example.mapper.HoneyGoodMapper;
import com.example.mapper.ProvincePeasonNumMapper;
import com.example.demo.*;
import com.example.entity.*;
@Controller
@SuppressWarnings("all")
public class HoneyGoodcontroller {
	//对文件做出修改
	@Autowired
	private StringRedisTemplate mystringtemplate;
	

	//引入Redis依赖
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redistemplate;

	@Autowired
	private HoneyGoodMapper honeyGoodMapper;
	
	@Autowired
	private CitiyAndPeasonMapper citiyandpeasonMapper;
	
	@Autowired
	private Citiy_idMapper citiy_idmapper;
	
	@Autowired
	private ProvincePeasonNumMapper provincePeasonNumMapper;
	
	
	public List<HoneyGood> getList() {
		
		
		//查redis数据库，是否存在key为"orderlist"的hash表
		//取得的值是数据类型为Linkedhashset的hashkey的一个集合listOps
		LinkedHashSet listOps = (LinkedHashSet) redistemplate.opsForHash().keys("orderlist");
		Long pageQueue = redistemplate.opsForList().size("pageQueue");
		
		System.out.println("完成入redis查询");
		System.out.println("");
		Iterator<String> listOpselmet=listOps.iterator();
		//准备迭代listOps
		//设置数据类型为String方便与数据库中的hashkey的类型对应
		//判断若listOps为空则redis中未存相关订单数据
		if(listOps.isEmpty()) {
			//从mysql中查询所有数据
			List<HoneyGood> list = honeyGoodMapper.selectAll();
			System.out.println("完成入mysql查询");
			for(int j =0;j<list.size();j++) {
				//将查到的数据全部存入redis中完成hash的创建内容为orderlis ：hash名，后面为key为ID的list集合
				redistemplate.opsForHash().put("orderlist", list.get(j).getThingsID(), list.get(j));
			}	
			System.out.println("完成入redis赋值");
			//将从数据库mysql中查到的list传递到前端视图页面
			Collections.sort(list);
		
			return list;
			
		}		
			else {	
				
				List<HoneyGood> redislis = new ArrayList();
				for(int i =0;i<listOps.size();i++) {
					//根据listOps将从redis中迭代取到的List存入到对象HoneyGood 对象（redislist）中					
					HoneyGood redislit = (HoneyGood) redistemplate.opsForHash().get("orderlist",listOpselmet.next());
					
					//再将对象存入对象列表
					redislis.add(redislit);
					Collections.sort(redislis);
					
					
				}
				
				return redislis;
			}
				

				
	}
	
	public int GetPageNum() {
		Integer peasonsum =honeyGoodMapper.selectPeasonNum(); 
		int pagesum =0;
		if(peasonsum>8) {
			if(peasonsum%8==0) {
				 pagesum = peasonsum/8;
			}else {
				pagesum = (peasonsum/8)+1;
			}
		}
		else {
			pagesum=1;
		}	
		return pagesum;
		
	}
	
	
	
	@RequestMapping("/index")
	public String IndexPage(ModelMap model) {
		List<HoneyGood> redislist = getList();
		Integer pagesum =GetPageNum();
		int currentpage = 1;
		model.addAttribute("currentpage",currentpage);
		model.addAttribute("pageNum", pagesum);
		int count = redislist.size()/9;
		if(count>0) {
			List<HoneyGood >oredilist = redislist.subList(0, 9);
			model.addAttribute("orderlist", oredilist);		 

			return "hello";
		}
		else {
			List<HoneyGood >   oredilist = 	redislist.subList(0, redislist.size());
			model.addAttribute("orderlist", oredilist);

			return "hello";
			 
		}

		
	}
	
	
	@RequestMapping("/nextpage/{currentpage}/{pageNum}")
	public String NextPage(@PathVariable int currentpage,@PathVariable int pageNum,ModelMap model) {
		List<HoneyGood> redislist = getList();	
		int finallist = 9;
			if(currentpage>pageNum){
				
				currentpage = pageNum;
			}
			
			if(currentpage*9<redislist.size()) {
				
				List<HoneyGood> oredilist = redislist.subList((currentpage-1)*9, currentpage*9);
				model.addAttribute("orderlist", oredilist);
				model.addAttribute("currentpage", currentpage);
			
			}
			
			else{
					System.out.println("进入到page方法");
					List<HoneyGood> oredilist = redislist.subList((currentpage-1)*9, redislist.size());
					if((currentpage-1)*9==redislist.size()) {
						model.addAttribute("currentpage",currentpage-1);
					}else {
						model.addAttribute("currentpage",currentpage);
					}
					model.addAttribute("orderlist", oredilist);	
			}
		
		
			
			model.addAttribute("pageNum", pageNum);
			

		return "hello";
	}
	
	
	
	
	@RequestMapping("/lastpage/{currentpage}/{pageNum}")
	public String LastPage(@PathVariable int currentpage,@PathVariable int pageNum,ModelMap model) {
		List<HoneyGood> redislist = getList();
		int finallist = 9;
		int count = redislist.size()/9;
		if(count>0) {
				if(currentpage>1) {
					List<HoneyGood> oredilist = redislist.subList((currentpage-1)*9, currentpage*9);
					model.addAttribute("orderlist", oredilist);
					model.addAttribute("currentpage", currentpage);
				}
				
				else{
					return "redirect:/index";
				}
			}
		model.addAttribute("pageNum", pageNum);
		
		return "hello";
		
	}
	
	@RequestMapping("/Gotopage/{pageNum}")
	public String GotoPage(@PathVariable int pageNum,ModelMap model) {
		List<HoneyGood> redislist = getList();
		Integer pagesum =GetPageNum();
		int currentpage = pageNum;
		model.addAttribute("pageNum", pagesum);
		
		
		if(pageNum*9>redislist.size()) {
			List<HoneyGood> oredilist = redislist.subList((pageNum-1)*9,redislist.size());
			model.addAttribute("orderlist", oredilist);
		}
		else {
			List<HoneyGood> oredilist = redislist.subList((pageNum-1)*9,pageNum*9);
			model.addAttribute("orderlist", oredilist);
		}
		
		model.addAttribute("currentpage", currentpage);
		
		
		return "hello";
	}
	
	
	@RequestMapping("/neworder")
	public String Neworder(ModelMap model) {
		
		
		List<Citiy_id> citis =citiy_idmapper.getAllCitiys();
	
		
		model.addAttribute("citis",citis);
		
		
		return "neworder";
	}	
	
	
	@RequestMapping("/honey/addneworder")
	@Transactional
	public String addneworders(HoneyGood honeygood){
		
		
		try {
			int ThingsID = (int)honeygood.getThingsID();
			honeygood.setThingsID(ThingsID);
			int Honey_price = (int)honeygood.getHoney_price();
			honeygood.setHoney_price(Honey_price);
			int Honey_weight = (int)honeygood.getHoney_weight();
			honeygood.setHoney_weight(Honey_weight);
			String keyid = String.valueOf(honeygood.getThingsID());
			redistemplate.opsForHash().put("orderlist", keyid, honeygood);
			
			int rows = honeyGoodMapper.addneworder(honeygood);
			
			//查询子城市表中是否存在城市名为honeygood.getHoney_buyer_secarea() 的父城市id
			Integer rowes = citiyandpeasonMapper.selectCitiy(honeygood.getHoney_buyer_area(),honeygood.getHoney_buyer_secarea());
			
			
			//非空意思为存在对应的父城市id即对应的子城市记录已经存在
			if(rowes!=null) {
				/*入redis查peason数量*/	
				
				redistemplate.opsForHash().increment("Citiy", honeygood.getHoney_buyer_secarea(), 1.00);
				
				/*从redis中取出想要的对应城市人数（因为redis数据库中increment方法中只接受double类型的数据，
				但我们持久化库mysql中存入的为int型，所以要强转
				*
				*/
				 int pnum = (int)redistemplate.opsForHash().get("Citiy", honeygood.getHoney_buyer_secarea());
				
				 
				 /*调用插入方法，将更新到redis中的数据持久化到mysql*/
				citiyandpeasonMapper.adpeaToCitiy(honeygood.getHoney_buyer_secarea(), honeygood.getHoney_buyer_area(),pnum );
			}else {
				//当统计人数的表中不存在对应的父城市id即为没有对应的子城市记录
				
				//则创建新的子城市-父城市id的记录
				//先取得父城市id
				Integer citi = citiy_idmapper.selectcitid(honeygood.getHoney_buyer_area());
			
				
				//因为人数统计表中设置的为联合主键，所以可以再父城市相同的情况下插入不同的子城市数据
				citiyandpeasonMapper.getnewScitiy(honeygood.getHoney_buyer_secarea(), citi, 1);
				
				
				//将更新的数据同步到redis数据库中
				redistemplate.opsForHash().put("Citiy", honeygood.getHoney_buyer_secarea(), 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}

		return "redirect:/index";		
	}
	
	@RequestMapping("/honey/nofishin")
	public String nofinshin(ModelMap model) {
		String Honey_setle="未结清";
			List nofinishlist = honeyGoodMapper.selectwjq(Honey_setle);
			model.addAttribute("nofinishlist",nofinishlist);
		
		return "nofinish";
		
	}
	
	@RequestMapping("/honey/delet/{orderlist}")
	@Transactional
	public String delet(@PathVariable String orderlist) {	
		String []orderArr = orderlist.split(",");
		//取Things的id
		
		String []newid = orderArr[0].split("=");
		
	try {
		int ID = Integer.parseInt(newid[1].toString());
		
		
		
		redistemplate.opsForHash().delete("orderlist", newid[1].toString());
		
		System.out.println("完成删除语句");
		
		honeyGoodMapper.deletorder(ID);
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
		return "redirect:/index";
	}
	
//	@RequestMapping("/backComsumer")
//	public List<ProvincePeasonNum> BackComsumer() {
//		
//		
//		
//		//'河北省','山西省','辽宁省','吉林省','黑龙江','江苏','浙江省','安徽省','福建省','台湾省','江西省','山东省','河南省','湖北省','湖南省','广东省','海南省','四川省','贵州省','云南省','陕西省','甘肃省','青海省','北京市','天津市','上海市','重庆市','内蒙古','广西','西藏','宁夏','新疆'
//		
//		return provinc;
//	}
	
	
	@RequestMapping("/honey/backComsumer")
	public String IntoComsumer(ModelMap model) {
		List<ProvincePeasonNum>  provinc = provincePeasonNumMapper.getBackConsumer();
		String [] provinc_name =new String[provinc.size()];
		int [] provinc_pnum = new int[provinc.size()];
		
		for(int i =0;i<provinc.size();i++) {
			provinc_name[i]=provinc.get(i).getCitiy();
			provinc_pnum[i]=provinc.get(i).getPeason_num();
		}
		String provinc_names = JSONArray.toJSONString(provinc_name);
		String provinc_nums = JSONArray.toJSONString(provinc_pnum);

		
		model.addAttribute("provinc_name",provinc_names);
		model.addAttribute("provinc_num",provinc_nums);
		
		
		
		return "backComsumer";
	}



}
