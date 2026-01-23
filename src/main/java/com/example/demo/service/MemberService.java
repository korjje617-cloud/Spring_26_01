package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	// 리포지터리 가져오고
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		// 입력받은 아이디가 있는지
		Member existsMember = getMemberByLoginId(loginId);
		if (existsMember != null) {
			return -1;
		}
		
		// 입력받은 이름이랑 이메일이 있는지
		existsMember = memberRepository.getMemberByNameAndEmail(name, email);
		if (existsMember != null) {
			return -2;
		}

		// 통과됐다면 가입시키고 방금 가입한 회원 정보 보내기
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInsertId();
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

}