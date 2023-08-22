package com.asianaidt.shoppingmall.controller;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.asianaidt.shoppingmall.dao.CartDAO;
import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;
import com.asianaidt.shoppingmall.exchange.Exchanges;
import com.asianaidt.shoppingmall.service.CartService;
import com.asianaidt.shoppingmall.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	private final UserService userService;

	@GetMapping("/cartList")
	public String cart(HttpSession session, Model m)throws Exception {

		UserDAO uDAO = (UserDAO) session.getAttribute("user");
		int pPrice = 0; 
		int dPrice = 0; 
		int payPrice = 0; 
		
		
		
		
		int exchange = (int) Exchanges.valueOf("KRW").getValue();
		
		
		String page = "redirect:/";
		if (uDAO != null) {
			List<CartDAO> cartList = cartService.cartList(uDAO.getUserid());
			m.addAttribute("cartList", cartList);
			

			for(int i=0; i<cartList.size(); i++) {
				pPrice+=cartList.get(i).getGPrice() * cartList.get(i).getGAmount();
			}

			for(int i=0; i<cartList.size(); i++) {
				dPrice += Math.round((cartList.get(i).getGPrice()/100.0 * cartList.get(i).getDiscount())) * cartList.get(i).getGAmount();
				
			}
			
			payPrice = pPrice - dPrice;
			
			m.addAttribute("pPrice", 0);
			m.addAttribute("dPrice", 0 );
			m.addAttribute("payPrice", 0);
			m.addAttribute("exchange", exchange);

			page = "cartList"; 
		}

		return page;
	}
	

	@GetMapping("/cart_org")
	public String cartList(HttpSession session, Model m)throws Exception {

		UserDAO uDAO = (UserDAO) session.getAttribute("user");
		int pPrice = 0; 
		int dPrice = 0; 
		int payPrice = 0; 

		int exchange = (int) Exchanges.valueOf("KRW").getValue();
		
		
		String page = "redirect:/";
		if (uDAO != null) {
			List<CartDAO> cartList = cartService.cartList(uDAO.getUserid());
			m.addAttribute("cartList", cartList);
			

			for(int i=0; i<cartList.size(); i++) {
				pPrice+=cartList.get(i).getGPrice() * cartList.get(i).getGAmount();
			}

			for(int i=0; i<cartList.size(); i++) {
				dPrice += Math.round((cartList.get(i).getGPrice()/100.0 * cartList.get(i).getDiscount())) * cartList.get(i).getGAmount();
				
			}
			
			payPrice = pPrice - dPrice;
			
			m.addAttribute("pPrice", 0);
			m.addAttribute("dPrice", 0 );
			m.addAttribute("payPrice", 0);
			m.addAttribute("exchange", exchange);
		
			page = "cart_org"; 
		}

		return page;
	}
	
	@GetMapping("/updateCart")
	public String updateCart(@RequestParam Map<String, Integer> map)throws Exception {
		cartService.updateCart(map);
		String page = "redirect:/cartList";
		
		return page;
	}
	
	@GetMapping("/delCart")
	public String delCart(@RequestParam("num") int num)throws Exception {
		cartService.delCart(num);
		
		return "redirect:/cartList";
	} 
	
	@GetMapping("/delAllCart")
	public String delAllCart(HttpServletRequest request)throws Exception  {		
		String[] checks = request.getParameterValues("check");	
		System.out.println(Arrays.toString(checks));
		List<String> list = Arrays.asList(checks);
		cartService.delAllCart(list);
		return "redirect:/cartList";
	}
	


	@PostMapping("/payment")
	public ModelAndView selectBuy(HttpServletRequest request, HttpSession session)throws Exception  {		
		ModelAndView mav = new ModelAndView();

		String[] checks = request.getParameterValues("check");	
		List<String> cartIdList = Arrays.asList(checks);
		
    	UserDAO user = (UserDAO) session.getAttribute("user");
    	UserDAO update = userService.findUserByEmail(user.getEmail());   	
    	
        List<OrderDTO> orderList = cartService.findCartList(cartIdList);
       int sum = cartService.findTotalPrice(cartIdList);
       DecimalFormat df = new DecimalFormat("###,###");
       String won = df.format(sum*1300);
       int intWon = sum*1300;
        session.setAttribute("cart", orderList);
        session.setAttribute("cnt", orderList.size());
        session.setAttribute("won", won);
        session.setAttribute("sum", sum);
        session.setAttribute("intWon", intWon);
        session.setAttribute("cartIdList", cartIdList);
        
        System.out.println(sum);
        
    	mav.addObject("user", update);
    	mav.addObject("sum", sum);
    	mav.addObject("won", won);
    	mav.addObject("orders", orderList);
    	mav.setViewName("payment");
    	
		return mav;
	}
}