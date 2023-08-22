package com.asianaidt.shoppingmall.controller;

import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.PurchaseDTO;
import com.asianaidt.shoppingmall.service.PurchaseService;
import com.asianaidt.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;
    
    @GetMapping("/purchase")
    public ModelAndView purchaseList(HttpSession session) throws Exception {
        UserDAO user = (UserDAO) session.getAttribute("user");
        int id = user.getId();
        List<PurchaseDAO> list = service.findSimplePurchase(id);
        System.out.println(list.size());
    	ModelAndView mav = new ModelAndView(); 
    	mav.setViewName("purchase");
    	mav.addObject("list", list);
    	mav.addObject("size", list.size());
        return mav;
    }
    
    @GetMapping("/purchase/detail")
    public ModelAndView retrieve(@RequestParam int purchaseid) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<PurchaseDTO> list = service.findPurchaseById(purchaseid);
        PurchaseDAO dao = service.findOne(purchaseid);
        mav.addObject("dao", dao);
        mav.addObject("list", list);
        mav.setViewName("purchasedetail");
        return mav;
    }
}
