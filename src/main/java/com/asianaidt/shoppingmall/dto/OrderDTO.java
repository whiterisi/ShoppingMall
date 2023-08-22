package com.asianaidt.shoppingmall.dto;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("OrderDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable{
	private int id;
	private int productId;
	private String name;
	private int price;
	private int salePrice;
	private int amount;
	private int sum;
	private String image;
}
