package com.asianaidt.shoppingmall.service;
import java.util.HashMap;
import java.util.List;

import com.asianaidt.shoppingmall.dto.ReviewDTO;




public interface ReviewService{
	public List<ReviewDTO> boardList() throws Exception;
	public int reviewWrite(ReviewDTO reviewdto)throws Exception;
	public int reviewModify(ReviewDTO reviewdto)throws Exception;
	public List<ReviewDTO> findReviewById(int productid) throws Exception;
	public int boardDelete(int id) throws Exception;
	public ReviewDTO boardByNum(int num) throws Exception;
	public int boardMultiDelete(List<Integer> list) throws Exception;
}