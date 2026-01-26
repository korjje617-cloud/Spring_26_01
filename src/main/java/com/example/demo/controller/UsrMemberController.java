package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "loginId 입력");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "loginPw 입력");
		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", "name 입력");
		}
		if (Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-4", "nickname 입력");
		}
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-5", "cellphoneNum 입력");
		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", "email 입력");
		}

		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return doJoinRd;
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return ResultData.newData(doJoinRd, "가입됨", member);
	}

	@RequestMapping("/usr/member/doLogIn")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession session, String loginId, String loginPw) {

		// 기본 로그아웃 상태
		boolean isLogined = false;

		// 로그인된 멤버 아이디가 널값이 아니라면
		if (session.getAttribute("loginedMemberId") != null) {
			// 로그인 상태
			isLogined = true;
		}

		// 이미 로그인 된 상태라면
		if (isLogined) {
			// 알려준다
			return ResultData.from("F-A", "이미 로그인 중");
		}

		// 입력된 값들이 null 인지 확인하라고 유틸에게 요청
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "아이디 작성해");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "비밀번호 작성해");
		}

		// 이 아이디를 가진 객체 가져오라고 요청
		Member member = memberService.getMemberByLoginId(loginId);

		// 틀렸을 때
		if (member == null) {
			return ResultData.from("F-3", Ut.f("%s 는 없는 아이디", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "틀린 비밀번호");
		}

		session.setAttribute("loginedMemberId", member.getId());

		// 위 과정을 다 통과해야 성공
		return ResultData.from("S-1", Ut.f("%s 님 로그인", member.getNickname()),"로그인 됨" , member);

	}

	@RequestMapping("/usr/member/doLogOut")
	@ResponseBody
	public ResultData<Member> doLogOut(HttpSession session, String loginId, String loginPw) {
		// 기본 로그아웃 상태
		boolean isLogined = false;

		// 로그인 된 상태라면 로그인 상태로 바꿔주고
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		// 이미 로그아웃 된 상태라면
		if (!isLogined) {
			// 알려준다
			return ResultData.from("F-A", "이미 로그아웃");
		}

		// 위 과정을 다 통과해야 성공
		session.removeAttribute("loginedMemberId");

		return ResultData.from("S-1", "로그아웃");

	}

}
