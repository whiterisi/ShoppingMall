package com.asianaidt.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asianaidt.shoppingmall.dao.ProductDAO;
import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.ProductDTO;
import com.asianaidt.shoppingmall.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	

	@GetMapping("/")
	public String products(@RequestParam(required=false, defaultValue="1") int curPage, Model model) throws Exception {
		
		ProductDTO productDTO = service.getList(curPage);
		model.addAttribute("productList", productDTO);
		return "main";
		}
		

	@GetMapping("/detail")
	public String productInfo(int productid, Model model) throws Exception {
		ProductDAO dao = service.findByProductID(productid);
		model.addAttribute("product", dao);
		return "product-single";
	}
	

	@GetMapping("/category")
	public String category(@RequestParam(required=false, defaultValue="1") int curPage, @RequestParam(required=false, defaultValue="1") int categoryid, Model model) throws Exception {
		ProductDTO list = service.getListByCategory(categoryid, curPage);
		model.addAttribute("productList", list);
		model.addAttribute("categoryid", categoryid);
		return "shop";
	}
	
	@GetMapping("/sort")
	public String sortProduct(@RequestParam HashMap<String, String> map, @RequestParam(required=false, defaultValue="1") int curPage, Model model) throws Exception {
		ProductDTO list = service.sortList(map, curPage);
		model.addAttribute("categoryid", map.get("categoryid"));
		model.addAttribute("productList", list);
		return "shop";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(required=false, defaultValue="1") int curPage, @RequestParam HashMap<String, String> map, Model model) throws Exception {
		ProductDTO list = service.searchList(map, curPage);
		model.addAttribute("productList", list);
		return "shop";
	}

	@PostMapping("/addCart")
	public String addCart(@RequestParam HashMap<String, String> map, @RequestParam String amount,  HttpSession session, Model model) throws Exception {
		UserDAO dao = (UserDAO) session.getAttribute("user");
		map.put("userid", dao.getUserid());
		map.put("gAmount", amount);
		System.out.println(map.get(""));
		try {
			int n = service.addCart(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProductDAO pdao = service.findByProductID(Integer.parseInt(map.get("productid")));
		model.addAttribute("product", pdao);
		return "product-single";
	}
}
