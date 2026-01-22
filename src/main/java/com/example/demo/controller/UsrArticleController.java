package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	@Autowired 
	private ArticleService articleService;

	// 생성자
	public UsrArticleController() {
		
	}

	// 액션 메서드------------------------------------------------
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {
		
		//서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		return article;
	}
	
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		
		//서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}
		
		// 서비스.작성 메서드로 article 작성
		articleService.modifyArticle(id, title, body);
		
		// 수정된 게시글을 객체에 저장
		article = articleService.getArticleById(id);
		
		return article;
	}
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		//서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 없습니다";
		}
		
		//서비스.삭제 메서드로 찾아온 article 삭제
		articleService.deleteArticle(id);
		
		return id + "번 게시글이 삭제되었습니다";
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		
		//서비스.작성 메서드로 article 만들어서 가져옴
		Article article = articleService.writeArticle(title, body);
		return article;
	}
	

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		//서비스에서 리스트 통으로 가져오기
		return articleService.getArticles();
	}

}
