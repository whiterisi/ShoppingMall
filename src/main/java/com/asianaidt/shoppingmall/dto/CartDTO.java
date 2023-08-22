package com.asianaidt.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("CartDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private int num;
	private String userid;
	private String gCode;
	private String gName;
	private int gPrice;
	private String gSize;
	private String gColor;
	private int gAmount;
	private String gImage;

}
