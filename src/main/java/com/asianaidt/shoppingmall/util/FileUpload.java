package com.asianaidt.shoppingmall.util;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.asianaidt.shoppingmall.dto.ReviewDTO;

@Component
public class FileUpload {
	 
	public boolean uploadReviewImg(ReviewDTO reviewDto) {
		
		System.out.println("1");
		MultipartFile file = reviewDto.getTheFile();
		
		System.out.println("))))))))"+file);
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadForder= Paths.get("C:", "delivery", "upload").toString();
		String imageUploadForder = Paths.get(file.getOriginalFilename(), today).toString();
		String uploadPath = Paths.get(uploadForder, imageUploadForder).toString();
		
		
		reviewDto.setReview_IMG(file.getOriginalFilename());
		
		File dir = new File(uploadPath);
		if (dir.exists() == false) {
			System.out.println("2");
			dir.mkdirs();
		}
		
		
		UUID uuid = UUID.randomUUID();
		String reviewImgName = uuid+"_"+reviewDto.getTheFile().getOriginalFilename(); 
	
		
		try {
			File target = new File(uploadPath, reviewImgName);
			reviewDto.getTheFile().transferTo(target);
			System.out.println("3");
		} catch (Exception e) {
			return false;
		}
		
		
		//reviewDto.setReview_IMG("upload\\"+imageUploadForder+"\\"+reviewImgName);
		System.out.println("4");
		
		return true;
	} 
}