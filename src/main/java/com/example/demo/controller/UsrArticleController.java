package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

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
	public ResultData getArticle(int id) {

		// 서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글입니다", id), article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		// 서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목 작성해");
		}
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "본문 작성해");
		}

		// 서비스.작성 메서드로 article 작성
		articleService.modifyArticle(id, title, body);

		// 수정된 게시글을 객체에 저장
		article = articleService.getArticleById(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글이 수정되었습니다", id), article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {

		// 서비스.찾기 메서드로 찾아온 article 이 들어감
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}

		// 서비스.삭제 메서드로 찾아온 article 삭제
		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글이 삭제되었습니다", id));
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {

		// 서비스에서 리스트 통으로 가져오기
		List<Article> articles = articleService.getArticles();

		int countArticles = articles.size();

		return ResultData.from("S-1", Ut.f("%d 개의 게시글", countArticles), articles);
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(HttpSession session, String title, String body) {

		// 로그인된 멤버 아이디가 널값이라면
		if (session.getAttribute("loginedMemberId") == null) {
			// 글 못씀
			return ResultData.from("F-A", "로그인을 하세요");
		}
		
		// 입력된 값이 null 인지 확인하라고 유틸에게 요청
		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목 작성해");
		}
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "본문 작성해");
		}
		
		ResultData writeArticleRd = articleService.writeArticle(title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);

		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

}
