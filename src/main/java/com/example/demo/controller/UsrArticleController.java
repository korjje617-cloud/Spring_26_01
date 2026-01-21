package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	// 전역변수로 메서드가 모두 사용 가능
	int lastArticleId;
	List<Article> articles;

	// 생성자
	public UsrArticleController() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		makeTestData();
	}

	/// 서비스 메서드------------------------------------------------

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}

	private Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;

		return article;
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;				
			}			
		}
		return null;
	}
	
	
	
	
	

	/// 액션 메서드------------------------------------------------

	// doAdd 주소 요청
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody // 응답
	public Article doAdd(String title, String body) {

		Article article = writeArticle(title, body);
		return article;

	}

	// doDelete 주소 요청
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody // 응답
	public String doDelete(int id) {

		Article article = getArticleById(id); // 리스트 안에서 파라미터 값으로 찾기
		
		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}
		
		articles.remove(article);
		return id + "번 게시글이 삭제되었습니다";
	}

	// getArticles 주소 요청
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody // 응답
	public List<Article> getArticles() {
		return articles;
	}
}
