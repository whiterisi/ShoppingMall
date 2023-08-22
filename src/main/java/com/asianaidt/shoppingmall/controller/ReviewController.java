package com.asianaidt.shoppingmall.controller;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.ReviewDTO;
import com.asianaidt.shoppingmall.service.ReviewService;


import lombok.Getter;
import lombok.Setter;


@Controller
public class ReviewController{
	@Autowired
	
	ReviewService reviewservice;
	
	@GetMapping("/list")
	public @ModelAttribute("boardList") List<ReviewDTO> list() {
		
		List<ReviewDTO> list=null;
		try {
			list = reviewservice.boardList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
		} // Model
		return list;   // Model
	}

//	@GetMapping("/list")
//	@ModelAttribute("pageDTO")
//	public PageDTO list(@RequestParam HashMap<String, String> map,
//			                   @RequestParam(defaultValue = "1", required = false) int curPage) throws Exception{
//		return reviewservice.list(map, curPage);
//	}
	
	@GetMapping("/writeForm")  
	public String writeForm(@RequestParam int num,  HttpSession session,Model m
			) throws Exception {
		
		
		
		try {
			UserDAO dao = (UserDAO)session.getAttribute("user");
			String userid = dao.getUserid();
			m.addAttribute("userid", userid);
			m.addAttribute("productid", num);
			return "write";
			}
		catch(Exception e) {
			
			
			return "redirect:list";
		}
		   // write.html
	}
//�뵳�됰윮 占쎌삂占쎄쉐
	@PostMapping("/write") 
	public String reviewWrite(HttpSession session,
			ReviewDTO reviewdto, Model m) throws Exception{
		//
			UserDAO dao = (UserDAO)session.getAttribute("user");
			String userid = dao.getUserid();
			reviewdto.setUser_id(userid);
			System.out.println(">>>>>>>>>>>>>" + reviewdto);
			int n = reviewservice.reviewWrite(reviewdto);
	
			
			return "redirect:list";
			
		}
	
	@PutMapping("/review") 
	public String reviewModify(HttpSession session,ReviewDTO reviewdto) throws Exception{
			System.out.println(reviewdto.toString());
			UserDAO dao = (UserDAO)session.getAttribute("user");
			String userid = dao.getUserid();
			reviewdto.setUser_id(userid);
			int n = reviewservice.reviewModify(reviewdto);
			return "redirect:list";
			
		}
	
	
	@GetMapping("/delete")  
	public String delete(@RequestParam(required = false, defaultValue = "1") 
	  int id, HttpSession session,
		ReviewDTO reviewdto) throws Exception {
		UserDAO dao = (UserDAO)session.getAttribute("user");
		String userid = dao.getUserid();
		reviewdto.setUser_id(userid);
		int n = reviewservice.boardDelete(id);
		
		return "redirect:list";     
	}
	
	@GetMapping("/multiDelete")  
	public String multiDelete(HttpServletRequest request) throws Exception {
		
		String [] checks=request.getParameterValues("check");
		Integer[] checkInt =
				 Stream.of(checks).mapToInt(Integer::parseInt)
				.boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(checkInt);
		System.out.println(list);

		int n = reviewservice.boardMultiDelete(list);
		
		return "redirect:list";     
	}
	@GetMapping("/retrieve")  
	public ModelAndView retrieve(@RequestParam(required = false, defaultValue = "1") 
	  int num) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		//紐⑤뜽 ���옣
		ReviewDTO dto = reviewservice.boardByNum(num);
		mav.addObject("retrieve", dto);
		//酉� ���옣
		mav.setViewName("retrieve");
		return mav;     
	}
	@ExceptionHandler({Exception.class})
	public String error() {
		return "error";  // error.html
	}
	
	@GetMapping("/review/list")
	@ResponseBody
	public List<ReviewDTO> reviewList(@RequestParam int product_id) throws Exception{
		System.out.println(product_id);
		List<ReviewDTO> list = reviewservice.findReviewById(product_id);
		System.out.println(">>>>>>>>>>>>>>" + list);
		return list;
	}
	
	
	
		


}
