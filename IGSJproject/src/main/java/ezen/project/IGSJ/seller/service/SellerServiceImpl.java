package ezen.project.IGSJ.seller.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;
import ezen.project.IGSJ.seller.dao.SellerDAO;

@Service
public class SellerServiceImpl implements SellerService{

	@Inject
	private SellerDAO sellerDAO;

	
	@Override
	public List<CategoryDTO> getCategory() throws Exception {
	
		return sellerDAO.getCategory();
	}


	@Override
	public void postRegister(ProductDTO product, ProductFileDTO productFile) throws Exception {
		sellerDAO.postRegister(product);
		sellerDAO.postImgRegister(productFile);
	}
	
	
	
}
