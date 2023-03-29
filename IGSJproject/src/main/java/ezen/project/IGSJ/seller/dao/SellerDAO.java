package ezen.project.IGSJ.seller.dao;

import java.util.List;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;

public interface SellerDAO {
	
	public List<CategoryDTO> getCategory()throws Exception;
	
	public void postRegister(ProductDTO product) throws Exception;
	public void postImgRegister(ProductFileDTO productFile) throws Exception;
}
