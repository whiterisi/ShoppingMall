package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.LoginDTO;
import com.asianaidt.shoppingmall.mapper.UserMapper;
import com.asianaidt.shoppingmall.oauth.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    
    @Override
	public int idCheck(String userid) throws Exception {
		return userMapper.idCheck(userid);
	}

    @Override
    public int signUp(UserDAO dao) throws Exception {
    	dao.setRole(Role.USER);
        return userMapper.signUp(dao);
    }


    @Override
    public UserDAO findByUseridAndPasswd(LoginDTO dto) throws Exception {
        return userMapper.findByUseridAndPasswd(dto);
    }

    @Override
    public UserDAO findUserByEmail(String email) throws Exception {
        return userMapper.findUserByEmail(email);
    }
    
    @Override
    @Transactional
	public int updateUser(UserDAO dao) throws Exception {
		return userMapper.updateUser(dao);
	}

    @Override
    @Transactional
    public int updateRandomPW(Map<String, String> map) throws Exception {
        return userMapper.updateRandomPW(map);
    }

	@Override
	public UserDAO mypage(String userid) throws Exception {
		return userMapper.mypage(userid);
    }
    
	@Transactional
	public int updateinfo(UserDAO dao) throws Exception {
		return userMapper.updateinfo(dao);
	}

}
