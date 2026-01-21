package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHomeController {
	/// http://localhost:8080/usr/home/주소?value=값
	
	@RequestMapping("/usr/home/getInt") // 이 주소로 요청이 들어온다면
	@ResponseBody // 이 아래 내용을 view 로 보여준다
	public int getInt() {
		return 1;
	}
	
	@RequestMapping("/usr/home/getStirng")
	@ResponseBody 
	public String getString() {
		return "abc";
	}
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody 
	public Map getMap() {
		Map <String, Object> myMap = new HashMap<>();
		myMap.put("철수나이", 10);
		return myMap;
	}
	
	@RequestMapping("/usr/home/getList") 
	@ResponseBody 
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("철수나이");
		return list;
	}

	@RequestMapping("/usr/home/getDouble")
	@ResponseBody 
	public double getDouble() {
		return 3.14;
	}
	
	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody 
	public boolean getBoolean() {
		return true;
	}
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody 
	public Article getArticle() {

		return new Article(1, "제목1", "내용1");
	}

}
