package com.asianaidt.shoppingmall.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.LoginDTO;
import com.asianaidt.shoppingmall.oauth.Role;
import com.asianaidt.shoppingmall.service.UserService;
import com.asianaidt.shoppingmall.service.UserSha256;

import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService; 
    
	@GetMapping("/login")
    public String loginForm() {
        return "login";
    }
	@GetMapping("/signup")
	public String signupForm() {
		return "signup";
	}
    
    @GetMapping(value="/idCheck", produces = "text/plain;charset=UTF-8")
	@ResponseBody 
	public String idCheck(@RequestParam("id") String userid) throws Exception {
		int result = userService.idCheck(userid); 
		System.out.println(result);
		String mesg = "사용가능";
		if(result==1 || userid.length()==0) {
			mesg = "사용불가";
		}
		return mesg;		
	}
    
    @PostMapping("/signUp")
    public String signUp(UserDAO dao) throws Exception {
    	String encryPassword = UserSha256.encrypt(dao.getPasswd());
    	dao.setPasswd(encryPassword);
    	userService.signUp(dao);
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO dto, Model m, HttpSession session) throws Exception {
    	String encryPassword = UserSha256.encrypt(dto.getPasswd());
    	dto.setPasswd(encryPassword);
        UserDAO dao = userService.findByUseridAndPasswd(dto);
        String page ="";
        if(dao!=null){
            session.setAttribute("user", dao);
            page ="redirect:/";
        }else{
            m.addAttribute("message", "회원정보가 일치하지 않습니다."); 
            page="login"; 
        }
        return page;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session) throws Exception{	
		UserDAO udao = (UserDAO) session.getAttribute("user");		
		UserDAO dao = userService.mypage(udao.getUserid());
		session.setAttribute("user", dao);		
		return "mypage";
	}
	@PostMapping("/updateUser")
	public String updateUser(UserDAO dao, Model m) {	
		int n = 0;
		try {
			n = userService.updateUser(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (n==1) {
			return "redirect:/";  
		} else {
			m.addAttribute("message", "회원정보수정 실패"); ;
			return "mypage";  
		}
	}	
}