package com.asianaidt.shoppingmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.shoppingmall.dao.CartDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;
import com.asianaidt.shoppingmall.mapper.CartMapper;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper mapper;
	
	@Override
	public List<CartDAO> cartList(String userid) throws Exception {
		return mapper.cartList(userid);
	}
	
	@Override
	public void updateCart(Map<String, Integer> map) throws Exception {
		mapper.updateCart(map);
	}

	@Override
	public void delCart(int num) throws Exception {
		mapper.delCart(num);
	}
	
	@Override
	public void delAllCart(List<String> list)  throws Exception{
		 mapper.delAllCart(list);
	}

	@Override
	public List<OrderDTO> findCartList(List<String> list) throws Exception {
		return mapper.findCartList(list);
	}

	@Override
	public int findTotalPrice(List<String> list) throws Exception {
		return mapper.findTotalPrice(list);
	}
	
}
