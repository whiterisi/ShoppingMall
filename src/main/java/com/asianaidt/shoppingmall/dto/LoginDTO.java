package com.asianaidt.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

import com.asianaidt.shoppingmall.dao.UserDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("LoginDTO")
public class LoginDTO {
    private String userid;
    private String passwd;
    
}
