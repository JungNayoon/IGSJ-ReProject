package ezen.project.IGSJ.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.admin.dao.AdminDAO;
import ezen.project.IGSJ.member.domain.MemberDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDAO adminDAO;
	
	// 전체 회원 불러오기
	@Override
	public List<MemberDTO> getAllUsers(int displayTotalContent, int pageContent, String searchType, String keyword) throws Exception {
		
		logger.info("관리자 페이지 전체 회원 불러오기 service");
		
		return adminDAO.getAllUsers(displayTotalContent, pageContent, searchType, keyword);
	}
	
	// 검색 결과에 따른 회원 출력
	@Override
	public int searchMember(String searchType, String keyword) throws Exception {
		
		logger.info("관리자 페이지 검색결과에 따른 회원 수 출력");
		
		return adminDAO.searchMember(searchType,keyword);
	}
	
	// 상품 목록 불러오기
	@Override
	public List<ProductDTO> getProductList(int displayTotalContent, int pageContent, String searchType, String keyword) throws Exception {
		
		logger.info("관리자 페이지 전체 상품 목록 불러오기 service");
		
		return adminDAO.getProductList(displayTotalContent, pageContent, searchType, keyword);
	}
	
	// 검색 결과에 따른 상품 불러오기
	@Override
	public int searchProduct(String searchType, String keyword) throws Exception {
		
		logger.info("관리자 페이지 검색결과에 따른 상품 수 출력");
		
		return adminDAO.searchProduct(searchType, keyword);
	}
	
	// 관리자 회원 정보 수정 실행
	@Override
	public void adminMemberModify(MemberDTO memberDTO, MemberAddressDTO memberAddressDTO) throws Exception {

		logger.info("관리자 회원 정보 수정 SERVICE");
		
		adminDAO.adminMemberModify(memberDTO, memberAddressDTO);
	}
	
	// 관리자 회원 선택에 따른 정보 가져오기
	@Override
	public MemberDTO adminSelectMember(String userId) throws Exception {
		
		logger.info("관리자 회원 정보 수정, 수정할 회원 아이디 : {}", userId);
		
		return adminDAO.adminSelectMember(userId);
	}
	@Override
	public MemberAddressDTO adminSelectAddress(String userId) throws Exception {
		
		return adminDAO.adminSelectAddress(userId);
	}
}
