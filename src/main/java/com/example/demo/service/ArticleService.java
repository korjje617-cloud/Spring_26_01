package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	// 리포지터리 객체 자체를 인수로 받는 생성자
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		// makeTestData();
	}

	// 테스트 데이터 만들기
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			articleRepository.writeArticle(title, body);
		}
	}


	// 파라미터로 게시글 작성
	public ResultData writeArticle(String title, String body) {

		articleRepository.writeArticle(title, body);
		
		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 게시글 작성", id), id);
	}

	// 게시글 찾아서 삭제
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	// 게시글 찾아서 수정
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
	
	// 리스트에서 id 로 게시글 찾기
	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}
	
	// 리스트 자체를 가지고 오기
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
}