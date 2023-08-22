package com.asianaidt.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.shoppingmall.dao.ProductDAO;
import com.asianaidt.shoppingmall.dto.ProductDTO;
import com.asianaidt.shoppingmall.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper mapper;
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public ProductDAO findByProductID(int productid) throws Exception {
		return mapper.findByProductID(productid);
	}
	
	@Override
	public ProductDTO getList(int curPage) throws Exception {
		return changetodto(curPage, "getList");
	}

	@Override
	public ProductDTO getListByCategory(int categoryid, int curPage) throws Exception {
		return changetodto(curPage, "getListByCategory", categoryid);
	}

	@Override
	public ProductDTO sortList(HashMap<String, String> map, int curPage) throws Exception {
		return changetodto(curPage, "sortList", map);
	}

	@Override
	public int addCart(HashMap<String, String> map) throws Exception {
		return mapper.addCart(map);
	}

	@Override
	public ProductDTO searchList(HashMap<String, String> map, int curPage) throws Exception {
		return changetodto(curPage, "searchList", map);
	}
	
	@Override
	public ProductDTO changetodto(int curPage, String s) throws Exception {
		ProductDTO productDTO = new ProductDTO();
		int perPage = productDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		
		List<ProductDAO> list = session.selectList("com.asianaidt.shoppingmall.mapper.ProductMapper."+s, null, new RowBounds(offset, productDTO.getPerPage()));
		
		int totalPage =	list.size()/perPage;
		if(list.size()%perPage != 0) totalPage++;
		
		productDTO.setCurPage(curPage);
		productDTO.setList(list);
		productDTO.setTotalPage(8);
		return productDTO;
	}
	
	@Override
	public ProductDTO changetodto(int curPage, String s, int i) throws Exception {
		ProductDTO productDTO = new ProductDTO();
		int perPage = productDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		
		List<ProductDAO> list = session.selectList("com.asianaidt.shoppingmall.mapper.ProductMapper."+s, i, new RowBounds(offset, productDTO.getPerPage()));
		
		int totalPage =	list.size()/perPage;
		if(list.size()%perPage != 0) totalPage++;
		
		productDTO.setCurPage(curPage);
		productDTO.setList(list);
		productDTO.setTotalPage(8);
		return productDTO;
	}
	
	@Override
	public ProductDTO changetodto(int curPage, String s, HashMap<String, String> map) throws Exception {
		ProductDTO productDTO = new ProductDTO();
		int perPage = productDTO.getPerPage();
		int offset = (curPage-1)*perPage;
		
		List<ProductDAO> list = session.selectList("com.asianaidt.shoppingmall.mapper.ProductMapper."+s, map, new RowBounds(offset, productDTO.getPerPage()));

		int totalPage =	list.size()/perPage;
		if(list.size()%perPage != 0) totalPage++;
		
		productDTO.setCurPage(curPage);
		productDTO.setList(list);
		productDTO.setTotalPage(totalPage);
		return productDTO;
	}
	
}

