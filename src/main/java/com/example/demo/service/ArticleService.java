package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.vo.Article;

@Service
public class ArticleService {
	
	// 전역변수
	private int lastArticleId;
	public List<Article> articles;

	// 생성자
	public ArticleService() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		makeTestData();
	}

	// 테스트 데이터 만들기
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}

	// 파라미터로 게시글 작성
	public Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;

		return article;
	}

	// 리스트에서 id로 게시글 찾기
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	// 게시글 찾아서 삭제
	public void deleteArticle(int id) {
		Article article = getArticleById(id);
		articles.remove(article);
		
	}
	
	// 게시글 찾아서 수정
	public void modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		
		article.setTitle(title);
		article.setBody(body);
	}
	

}