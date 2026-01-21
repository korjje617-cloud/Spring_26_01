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

	// 서비스 메서드------------------------------------------------

	// 테스트 데이터 만들기
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}

	// 파라미터로 게시글 작성
	private Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;

		return article;
	}

	// 리스트에서 id로 게시글 찾기
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	// 액션 메서드------------------------------------------------

	// doAdd 주소 요청
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody // 응답
	public Article doAdd(String title, String body) {

		// 파라미터에서 값 받아서 게시글 쓰기
		Article article = writeArticle(title, body);
		return article;

	}

	// doDelete 주소 요청
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody // 응답
	public String doDelete(int id) {

		// 리스트 안에서 파라미터 값으로 찾기
		Article article = getArticleById(id);

		// 만약 없으면 알림
		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		// 있다면 삭제
		articles.remove(article);
		return id + "번 게시글이 삭제되었습니다";
	}

	// doModify 주소 요청
	@RequestMapping("/usr/article/doModify")
	@ResponseBody // 응답
	public Object doModify(int id, String title, String body) {

		// 리스트 안에서 파라미터 값으로 찾기
		Article article = getArticleById(id);

		// 만약 없으면 알림
		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		// 있다면 파라미터에서 값 받아서 수정
		article.setTitle(title);
		article.setBody(body);

		return id + "번 게시글이 수정되었습니다<br>" + " 수정된 게시글 : " + article;
		// 가독성이 별로여서 개행하려고 했는데 \n 이 안먹혔다
		// 그래서 검색해보니까 <br> 쓰라고 해서 써봤더니 정상 적용
	}

	// getArticle 주소 요청
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody // 응답

	public Object getArticle(int id) {

		// 리스트 안에서 파라미터 값으로 찾기
		Article article = getArticleById(id);

		// 만약 없으면 알림
		if (article == null) {
			return id + "번 게시글은 없습니다.";
		}

		// 있다면 보여줌
		return article;
	}

	// getArticles 주소 요청
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody // 응답

	public List<Article> getArticles() {
		// 게시글 리스트 출력
		return articles;
	}

}
