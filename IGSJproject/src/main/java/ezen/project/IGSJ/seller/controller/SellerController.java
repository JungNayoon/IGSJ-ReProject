package ezen.project.IGSJ.seller.controller;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;



import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;
import ezen.project.IGSJ.seller.service.SellerService;
import ezen.project.IGSJ.utils.UploadFileUtils;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/seller")
public class SellerController {
	
	@Inject
	private SellerService sellerService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void getRegister(Model model) throws Exception{
		
		List<CategoryDTO> category = null;
		category = sellerService.getCategory();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String postRegister(ProductDTO product, ProductFileDTO productFile, MultipartFile file)throws Exception {
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;
		String rs = RandomStringUtils.randomAlphanumeric(20);
		
		product.setPno(rs);
		productFile.setPno(rs);
		product.setUserId("1111");
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
		 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else{
		 fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		productFile.setOriginalFileName(fileName);
		productFile.setStoredFileRootName(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		productFile.setStoredThumbNailName(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
	
		sellerService.postRegister(product,productFile);
		
		return "/";
	}
}
