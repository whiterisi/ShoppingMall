package com.asianaidt.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("SignUpDTO")
public class SignUpDTO {
    private String userid;
    private String email;
    private String passwd;
}
