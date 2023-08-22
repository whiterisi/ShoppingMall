package com.asianaidt.shoppingmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.ProductDTO;
import com.asianaidt.shoppingmall.exchange.Exchanges;
import com.asianaidt.shoppingmall.service.ProductService;
import com.asianaidt.shoppingmall.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final UserService userService;
	private final ProductService productService;
	
    @GetMapping("/")
    public String main(Model model) throws Exception{
    	int curPage = 1;
    	ProductDTO productDTO = 
    			productService.getList(curPage);
    	int exchange = (int) Exchanges.valueOf("KRW").getValue();
    	
		model.addAttribute("productList", productDTO);
		return "index";

    }
    @PostMapping("/flight")
    public String flight(UserDAO dao, HttpSession session) throws Exception{
    	UserDAO user = (UserDAO) session.getAttribute("user");
    	user.update(dao.getPassport(), dao.getBirthdate(), dao.getFirstname(),dao.getLastname(), dao.getUsername(), dao.getPhone());
    	session.setAttribute("user", user);
    	userService.updateinfo(user);
        return "flight";
    }
    @RequestMapping("/email")
    public String email(){
        return "email";
    }

}
