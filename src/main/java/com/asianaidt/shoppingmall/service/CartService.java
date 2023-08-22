package com.asianaidt.shoppingmall.service;

import java.util.List;
import java.util.Map;

import com.asianaidt.shoppingmall.dao.CartDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;

public interface CartService {
	
	//public int addCart(CartDTO dto)throws Exception;
	public List<CartDAO> cartList(String userid)throws Exception;
	public void updateCart(Map<String, Integer> map)throws Exception;
	public void delCart(int num)throws Exception;
	public void delAllCart(List<String> list) throws Exception ;
	/*
	 * public CartDTO cartbyNum(String num)throws Exception ; 
	 * public void orderDone(OrderDTO dto, String orderNum)throws Exception ;
	 */
	public List<OrderDTO> findCartList(List<String> list) throws Exception;
	public int findTotalPrice(List<String> list) throws Exception;
}
