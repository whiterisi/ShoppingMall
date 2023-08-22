package com.asianaidt.shoppingmall.dto;

import java.util.List;

import com.asianaidt.shoppingmall.dao.ProductDAO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private List<ProductDAO> list;
	private int perPage = 40;
	private int totalPage = 6;
	private int curPage;
}
