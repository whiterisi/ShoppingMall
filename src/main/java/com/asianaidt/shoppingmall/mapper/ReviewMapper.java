package com.asianaidt.shoppingmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.shoppingmall.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {
	public List<ReviewDTO> boardList() throws Exception;
	//由щ럭 �옉�꽦
	public int reviewWrite(ReviewDTO reviewdto) throws Exception;
		
	//由щ럭 �닔�젙
	public void reviewModify(ReviewDTO reviewdto) throws Exception;
	public int boardDelete(int id) throws Exception;
	public ReviewDTO boardByNum(int num) throws Exception;
	public int boardMultiDelete(List<Integer> list) throws Exception;
	public List<ReviewDTO> findReviewById(int productid) throws Exception;
}

