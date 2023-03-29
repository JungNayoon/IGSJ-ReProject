package ezen.project.IGSJ.member.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.member.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAME_SPACE = "mappers.memberMapper";
	
	//회원가입 로직
	@Override
	public void signUpMember(MemberDTO memberDTO) throws Exception {

		logger.info("회원가입 실행 signUpMember - DAO");
		
		sqlSession.insert(NAME_SPACE + ".signUpMember", memberDTO);
	}
	
	//로그인 기능 구현
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception {

		logger.info("로그인 실행 memberLogin - DAO");
		
		return sqlSession.selectOne(NAME_SPACE + ".memberLogin", memberDTO);
	}
	
	// 회원가입 아이디 중복 체크
	@Override
	public int memberIdCheck(MemberDTO memberDTO) throws Exception {

		logger.info("회원가입 아이디 중복 체크 memberIdCheck - DAO");
		
		return sqlSession.selectOne(NAME_SPACE + ".memberIdCheck", memberDTO);
		
	}
	
	//회원정보 수정 로직
	@Override
	public void memberModify(MemberDTO memberDTO) throws Exception {

		logger.info("회원정보 수정 로직 memberModify - DAO");
		
		sqlSession.update(NAME_SPACE + ".memberModify", memberDTO);
	}
	//회원 정보 찾기
	@Override
	public MemberDTO memberProfile(String userId) throws Exception {
		
		logger.info("회원 정보 찾기 memberProfile - DAO");
		
		return sqlSession.selectOne(NAME_SPACE + ".memberProfile", userId);
	}
}
