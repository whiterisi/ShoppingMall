package com.asianaidt.shoppingmall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.shoppingmall.dao.CartDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;

@Mapper
public interface CartMapper{
	
	
	public List<CartDAO> cartList(String userid)throws Exception;
	public int updateCart(Map<String, Integer> map)throws Exception;
	public int delCart(int num)throws Exception;
	public int delAllCart(List<String> list) throws Exception; 
	
	public int findTotalPrice(List<String> list) throws Exception;
	public List<OrderDTO> findCartList(List<String> list) throws Exception;
}
