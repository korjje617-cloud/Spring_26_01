package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;


@Controller
public class UsrHomeController {

	int lastArticleId;
	List<Article> articles;
	// 전역변수로 메서드가 모두 사용 가능

	
	public UsrHomeController() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		makeTestData();
	} // 생성자
	
	
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			int id = lastArticleId + 1;
			String title = "제목" + i;
			String body = "내용" + i;
			
			Article article = new Article(id, title, body);
			articles.add(article);
			lastArticleId++;
		} // 테스트 데이터 자동생성

	}


	// doAdd 주소 요청
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody // 응답
	public Article doAdd(String title, String body) {
		
		int id = lastArticleId + 1;
		
		Article article = new Article(id, title, body); // 파라미터 값 받기
		articles.add(article);
		lastArticleId++;
		
		return article;
	}
	
	

	
	
	
	// getArticles 주소 요청
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody // 응답
	public List<Article> getArticles() {
		return articles;
	}
}
