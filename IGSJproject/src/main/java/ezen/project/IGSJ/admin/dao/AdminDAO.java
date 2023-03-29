package ezen.project.IGSJ.admin.dao;

import java.util.List;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.member.domain.MemberDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;

public interface AdminDAO {
	
	// 전체 회원 불러오기
	public List<MemberDTO> getAllUsers(int displayTotalContent, int pageContent, String searchType, String keyword) throws Exception;
	
	// 검색 결과에 따른 회원 수 출력
	public int searchMember(String searchType, String keyword) throws Exception;
	
	// 전체 상품 불러오기
	public List<ProductDTO> getProductList(int displayTotalContent, int pageContent, String searchType, String keyword) throws Exception;
	
	// 검색 결과에 따른 회원 수 출력
	public int searchProduct(String searchType, String keyword) throws Exception;
	
	// 관리자 회원 정보 수정
	public void adminMemberModify(MemberDTO memberDTO, MemberAddressDTO memberAddressDTO) throws Exception;
	// public void adminAddressModify(MemberAddressDTO memberAddressMemberDTO) throws Exception;
		
	// 관리자 회원 선택에 따른 정보 가져오기
	public MemberDTO adminSelectMember(String userId) throws Exception;
	public MemberAddressDTO adminSelectAddress (String userId) throws Exception;
}
