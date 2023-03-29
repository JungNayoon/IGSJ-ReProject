package ezen.project.IGSJ.seller.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;

@Repository
public class SellerDAOImpl implements SellerDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "mappers.sellerMapper";

	
	@Override
	public List<CategoryDTO> getCategory() throws Exception {
		return sqlSession.selectList(Namespace+".getCategory");
	}


	@Override
	public void postRegister(ProductDTO product) throws Exception {
		sqlSession.insert(Namespace+".postRegister",product);
	}
	@Override
	public void postImgRegister(ProductFileDTO productFile) throws Exception {
		sqlSession.insert(Namespace+".imgRegister",productFile);
	}
	
	
	
}
