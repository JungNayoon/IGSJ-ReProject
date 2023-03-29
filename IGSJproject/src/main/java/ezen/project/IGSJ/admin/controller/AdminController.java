package ezen.project.IGSJ.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.admin.service.AdminService;
import ezen.project.IGSJ.member.domain.MemberDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.utils.pagination.PageIngredient;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	// 관리자 페이지 입장
	@RequestMapping(value = "/admin/mainpage", method = RequestMethod.GET)
	public void adminMainPage() throws Exception {
		
		logger.info("관리자 페이지 입장");
		
	}
	
	// 전체 회원 불러오기
	@RequestMapping(value = "/admin/memberlist", method = RequestMethod.GET)
	public void getAllUsers(@RequestParam("pageNum") int pageNum,
			@RequestParam(value = "searchType", required = false, defaultValue = "userId") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, PageIngredient page, Model model) throws Exception {

		logger.info("관리자 페이지 - 회원 목록 출력 getAllUsers - controller");

		logger.info("관리자 페이지 - 회원 목록 출력 검색타입: {} , 검색어 : {} ", searchType, keyword);

		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(10, 10, 10);

		page.setPageNum(pageNum);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		page.setSearchTypeAndKeyword(searchType, keyword);

		// 게시글 총 갯수를 구한다. 단 검색타입과 키워드에 맞춘 결과에 대한 총 갯수를 출력해야한다.
		page.setTotalContent(adminService.searchMember(searchType, keyword));

		List<MemberDTO> adminMemberList = null;
		adminMemberList = adminService.getAllUsers(page.getSelectContent(), page.getContentNum(), searchType, keyword);
		model.addAttribute("adminMemberList", adminMemberList);
		model.addAttribute("page", page);

		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);

	}
	
	// 전체 상품 목록 불러오기
	@RequestMapping(value = "/admin/productlist", method = RequestMethod.GET)
	public void getProductList(@RequestParam("pageNum") int pageNum,
			@RequestParam(value = "searchType", required = false, defaultValue = "product_name") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, PageIngredient page, Model model) throws Exception {

		logger.info("관리자 페이지 - 상품 목록 출력 getProductList - controller");

		logger.info("관리자 페이지 - 상품 목록 출력 검색타입: {} , 검색어 : {} ", searchType, keyword);

		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(5, 5, 5);

		page.setPageNum(pageNum);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		page.setSearchTypeAndKeyword(searchType, keyword);

		// 게시글 총 갯수를 구한다. 단 검색타입과 키워드에 맞춘 결과에 대한 총 갯수를 출력해야한다.
		page.setTotalContent(adminService.searchProduct(searchType, keyword));

		List<ProductDTO> adminProductList = null;
		adminProductList = adminService.getProductList(page.getSelectContent(), page.getContentNum(), searchType, keyword);
		model.addAttribute("adminProductList", adminProductList);
		model.addAttribute("page", page);

		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);

	}
	
	// 관리자 회원 정보 수정 페이지 진입
	@RequestMapping(value = "/admin/membermodifypage", method = RequestMethod.GET)
	public String adminMemberModifyPage(
			@RequestParam("userId") String userId, 
			MemberDTO memberDTO, 
			MemberAddressDTO memberAddressDTO,
			Model model) throws Exception {
		
		logger.info("관리자 회원 정보 수정 페이지 접속");
		
		memberDTO = adminService.adminSelectMember(userId);
		
		memberAddressDTO = adminService.adminSelectAddress(userId);
		
		model.addAttribute("userInfo", memberDTO);
		model.addAttribute("userAddressInfo", memberAddressDTO);
		
		return "/admin/membermodify";
	}
	
	// 관리자 회원 정보 수정 실행
	@RequestMapping(value = "/admin/membermodify", method = RequestMethod.POST)
	public String adminMemberModify(MemberDTO memberDTO , MemberAddressDTO memberAddressDTO , HttpSession session) throws Exception {
		
		logger.info("관리자 회원 정보 수정 시작");
		
		adminService.adminMemberModify(memberDTO, memberAddressDTO);
		
		//adminService.adminAddressModify(memberAddressDTO);
		
		return "redirect:/admin/memberlist?pageNum=1";
	}
	
	// 관리자 회원 삭제 ajax
}
