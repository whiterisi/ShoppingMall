package com.asianaidt.shoppingmall.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.shoppingmall.dao.ProductDAO;

@Mapper
public interface ProductMapper {
	
	public ProductDAO findByProductID(int productid) throws Exception;
	public List<ProductDAO> getList() throws Exception;
	public List<ProductDAO> getListByCategory(int categoryid) throws Exception;
	public List<ProductDAO> sortList(HashMap<String, String> map) throws Exception;
	public int addCart(HashMap<String, String> map) throws Exception;
	public List<ProductDAO> searchList(HashMap<String, String> map) throws Exception;
}
