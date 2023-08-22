package com.asianaidt.shoppingmall.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("ReviewDTO")
public class ReviewDTO {
	private int id;
	private String user_id;
	private String order_num; 
	private String product_id;
	private String title;
	private String review_content;
	private Date regi_date;
	private int score;
	private String review_IMG;	
	private MultipartFile theFile;
	
	
}
