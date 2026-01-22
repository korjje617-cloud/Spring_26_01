package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Component
public class ArticleRepository {
	
	// 전역변수
	private int lastArticleId;
	public List<Article> articles;

	public ArticleRepository() {
		articles = new ArrayList<>();
		lastArticleId = 0;
	}

	// 생성자
	public Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);

		lastArticleId++;

		return article;
	}
	
	// 게시글 삭제 기능
	public void deleteArticle(int id) {
		Article article = getArticleById(id);
		articles.remove(article);

	}
	
	// 게시글 수정 기능
	public void modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		article.setTitle(title);
		article.setBody(body);

	}

	// 아이디로 게시글 찾기 기능
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	// 게시글 리스트 싹 가져오는 기능
	public List<Article> getArticles() {
		return articles;
	}

}