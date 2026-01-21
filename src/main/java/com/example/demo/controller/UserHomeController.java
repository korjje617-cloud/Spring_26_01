package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {

	int count = 0; // 전역이라서 전체 사용 가능

	@RequestMapping("/usr/home/main") // 이 주소로 요청이 들어온다면
	@ResponseBody // 이 아래 내용을 view 로 보여준다
	public String showMain() {
		return "안녕하세요";
	}

	@RequestMapping("/usr/home/main2") 
	@ResponseBody 
	public String showMain2() {
		return "잘가";
	}

	@RequestMapping("/usr/home/main3") 
	@ResponseBody 
	public String showMain3() {
		return String.valueOf(1 + 2);
	}

	@RequestMapping("/usr/home/getCount") 
	@ResponseBody 
	public int getCount() {
		return count++; // 새로고침 시 카운트 증가
	}

	@RequestMapping("/usr/home/setCount") 
	@ResponseBody 
	public String setCount() {
		count = 0; // 전역 count +으로 초기화
		return "count 값 0으로 초기화";
	}
	
	@RequestMapping("/usr/home/setCountValue") 
	@ResponseBody 
	public String setCountValue(int value) { // http://localhost:8080/usr/home/setCountValue?value=10
		this.count = value; // 전역 count value 로 초기화
		return "count 값" + value + "(으)로 초기화";
	}
}
