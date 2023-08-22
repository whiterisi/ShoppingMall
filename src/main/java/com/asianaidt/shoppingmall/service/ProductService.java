package com.asianaidt.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import com.asianaidt.shoppingmall.dao.ProductDAO;
import com.asianaidt.shoppingmall.dto.ProductDTO;

public interface ProductService {
	public ProductDAO findByProductID(int productid) throws Exception;
	public ProductDTO getList(int curPage) throws Exception;
	public ProductDTO getListByCategory(int categoryid, int curPage) throws Exception;
	public ProductDTO sortList(HashMap<String, String> map, int curPage) throws Exception;
	public int addCart(HashMap<String, String> map) throws Exception;
	public ProductDTO searchList(HashMap<String, String> map, int curPage) throws Exception;
	public ProductDTO changetodto(int curPage, String s) throws Exception;
	public ProductDTO changetodto(int curPage, String s, int i) throws Exception;
	public ProductDTO changetodto(int curPage, String s, HashMap<String, String> map) throws Exception;
}
