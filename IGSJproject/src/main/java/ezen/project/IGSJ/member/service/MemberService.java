package ezen.project.IGSJ.member.service;

import ezen.project.IGSJ.member.domain.MemberDTO;

public interface MemberService {

	//회원가입 로직
	public void signUpMember(MemberDTO memberDTO) throws Exception;
	
	//로그인 기능
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception;
	
	// 회원가입 아이디 중복 체크
	public int memberIdCheck(MemberDTO memberDTO) throws Exception;

	//회원정보 수정 로직
	public void memberModify(MemberDTO memberDTO) throws Exception;
	
	// 회원 정보 찾기
	public MemberDTO memberProfile(String userId) throws Exception;
}
