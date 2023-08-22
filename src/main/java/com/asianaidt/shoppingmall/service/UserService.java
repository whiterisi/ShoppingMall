package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.LoginDTO;

import java.util.Map;

public interface UserService{
		public int idCheck(String userid) throws Exception;
        public int signUp(UserDAO dao) throws Exception;
        public UserDAO findByUseridAndPasswd(LoginDTO dao) throws Exception;
        public UserDAO findUserByEmail(String email) throws  Exception;
        public int updateUser(UserDAO dao) throws Exception;
        public int updateRandomPW(Map<String, String> map) throws Exception;
        public UserDAO mypage(String userid) throws Exception;
        public int updateinfo(UserDAO dao) throws Exception;

}
