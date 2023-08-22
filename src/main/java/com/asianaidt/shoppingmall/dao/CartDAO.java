package com.asianaidt.shoppingmall.dao;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CartDAO")
public class CartDAO {
	private int num;
	private String userid;
	private int gCode; //private int productid;
	private String gName;
	private int gPrice;
	private int gAmount;
	private String gImage;
	private int discount;
	private int inventory;
}
