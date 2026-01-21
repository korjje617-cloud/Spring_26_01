package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	@RequestMapping("/usr/home/main") // 이 주소로 요청이 들어온다면
	@ResponseBody // 이 아래 내용을 view 로 보여준다
	public String showMain( ) {
		return "안녕하세요";
	}
}
