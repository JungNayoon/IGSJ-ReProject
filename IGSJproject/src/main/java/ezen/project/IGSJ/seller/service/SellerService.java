package ezen.project.IGSJ.seller.service;

import java.util.List;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;

public interface SellerService {

	public List<CategoryDTO> getCategory() throws Exception;
	
	public void postRegister(ProductDTO product, ProductFileDTO productFile) throws Exception;
	
}
