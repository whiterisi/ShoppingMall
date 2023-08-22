package com.asianaidt.shoppingmall.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asianaidt.shoppingmall.dto.ReviewDTO;
import com.asianaidt.shoppingmall.mapper.ReviewMapper;
import com.asianaidt.shoppingmall.util.FileUpload;

import lombok.Getter;
import lombok.Setter;


@Service
public class ReviewServiceimpl implements ReviewService{
	@Autowired
	ReviewMapper reviewmapper;
	
	@Autowired
	FileUpload fileupload;
	//由щ럭�옉�꽦
	
	@Transactional
	public int reviewWrite(ReviewDTO reviewdto) throws Exception {
			
			
			if(!fileupload.uploadReviewImg(reviewdto)) {
					return 0;
			}
			reviewmapper.reviewWrite(reviewdto);
			return 1;
		}

	@Override
	public List<ReviewDTO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return reviewmapper.boardList();
		
	}
	public ReviewDTO boardByNum(int num) throws Exception {
		return reviewmapper.boardByNum(num);
	}
	@Override
	public int reviewModify(ReviewDTO reviewdto) throws Exception {
		// TODO Auto-generated method stub
		if(reviewdto.getTheFile() == null) {
			reviewdto.setReview_IMG("");
		}
		else {
			if(!fileupload.uploadReviewImg(reviewdto))
				return 0;
		}
		reviewmapper.reviewModify(reviewdto);
		return 1;
		
		
	}
		
		//由щ럭�닔�젙
	@Transactional
	@Override
	public int boardDelete(int id) throws Exception {
		
		return reviewmapper.boardDelete(id);
	}

	@Override
	public int boardMultiDelete(List<Integer> list) throws Exception {
		return reviewmapper.boardMultiDelete(list);
	}

	@Override
	public List<ReviewDTO> findReviewById(int productid) throws Exception {
		// TODO Auto-generated method stub
		return reviewmapper.findReviewById(productid);
	}
	

	
}