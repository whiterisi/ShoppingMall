package com.asianaidt.shoppingmall.oauth;


import org.springframework.stereotype.Service;
import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.ObjectDTO;
import com.asianaidt.shoppingmall.mapper.UserMapper;
import com.asianaidt.shoppingmall.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final UserMapper userMapper;
    public int signUpGoogle(UserDAO dao) throws Exception {
    	String userid = dao.getEmail().split("@")[0]+EmailService.getRandomPassword(5);
    	dao.setUserid(userid);
        return userMapper.signUpGoogle(dao);
    }
    public UserDAO findUserByEmail(String email) throws Exception {
        return userMapper.findUserByEmail(email);
    }

}
