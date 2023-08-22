package com.asianaidt.shoppingmall.dto;

import com.asianaidt.shoppingmall.dao.ProductDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {
	private ProductDAO dao;
	private int cnt;
	private int size;
}
