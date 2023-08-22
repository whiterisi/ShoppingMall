package com.asianaidt.shoppingmall.dao;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ProductDAO")
public class ProductDAO {

    private int productid;
   private  String name;
   private  String brand;
   private  int price;
    private int category;
    private int sales;
    private String image;
    private String image2;
    private int discount;
    private int inventory;
    
	public ProductDAO(int productid, String name, String brand, int price, int category) {
		super();
		this.productid = productid;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.category=category;
	}
    

}

