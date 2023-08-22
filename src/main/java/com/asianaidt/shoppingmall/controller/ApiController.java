package com.asianaidt.shoppingmall.controller;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.FlightDTO;
import com.asianaidt.shoppingmall.dto.FligthSearchDTO;
import com.asianaidt.shoppingmall.dto.ObjectDTO;
import com.asianaidt.shoppingmall.dto.OrderDTO;
import com.asianaidt.shoppingmall.kakaopay.KakaoPayService;
import com.asianaidt.shoppingmall.service.CartService;
import com.asianaidt.shoppingmall.service.EmailService;
import com.asianaidt.shoppingmall.service.FlightService;
import com.asianaidt.shoppingmall.service.PurchaseService;
import com.asianaidt.shoppingmall.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

	private final KakaoPayService kakaoPayService;
	private final FlightService flightService;
	private final EmailService emailService;
    private final UserService userService;  
    private final PurchaseService PurchaseService;
    private final CartService cartService;

    @GetMapping("/kakaoPay")
    public String kakaoPay(HttpSession session, HttpServletRequest request) {
    	String[] check = request.getParameterValues("check");
    	String internationalNum = check[0];
        session.setAttribute("flight", internationalNum);

        UserDAO userdao = (UserDAO)session.getAttribute("user");
    	int userid = userdao.getId();
    	String purchasedate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	List<OrderDTO> orderList = (List<OrderDTO>)session.getAttribute("cart");
    	String amount = String.valueOf(session.getAttribute("won"));
        
        emailService.sendOrderMail(userdao, orderList, amount, purchasedate);  	
        return "redirect:" + kakaoPayService.kakaoPayReady(session);
    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token,  Model model, HttpSession session) throws Exception {
    	UserDAO userdao = (UserDAO)session.getAttribute("user");
    	int userid = userdao.getId();
    	String flightid = String.valueOf(session.getAttribute("flight"));
    	String purchasedate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	List<OrderDTO> orderList = (List<OrderDTO>)session.getAttribute("cart");
    	String amount = String.valueOf(session.getAttribute("won"));
    	int cnt = Integer.parseInt(String.valueOf(session.getAttribute("cnt")));

    	PurchaseDAO dao =new PurchaseDAO(userid,flightid,purchasedate,amount, cnt);
    	PurchaseService.addPurchase(dao, orderList); 
    	List<String> cartIdList=(List<String>) session.getAttribute("cartIdList");
    	cartService.delAllCart(cartIdList);   	
    	
        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token, session));
        return "confirm";
    }

    @PostMapping("/flight")
    public ModelAndView flight(FligthSearchDTO dto) throws IOException {
        ModelAndView mav = new ModelAndView();
        List<FlightDTO> list = flightService.flight(dto);
        mav.addObject("list", list);
        mav.addObject("status", list.isEmpty()? 1:2);
        mav.setViewName("forward:/flight");
        return mav;
    }


    @PostMapping("/mail")
    public String newPassword(String email) throws Exception {
        UserDAO user = userService.findUserByEmail(email);
        
        if(user==null){
        	
        }else {
            String newPw = emailService.getRandomPassword(10);
            Map<String, String> map = new HashMap<>();
            map.put("passwd", newPw);
            map.put("email", email);
            userService.updateRandomPW(map);
            emailService.sendMail(user, newPw);
        }
        return "login";
    }

}
