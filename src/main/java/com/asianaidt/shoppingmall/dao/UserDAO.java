package com.asianaidt.shoppingmall.dao;

import com.asianaidt.shoppingmall.oauth.Role;
import org.apache.ibatis.type.Alias;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("UserDAO")
public class UserDAO {
	private int id;
	private String userid;
	private String passwd;
	private String username;
	private String email;
	private String phone;
	private String passport;
	private String birthdate;
	private String firstname;
	private String lastname;
    private Role role;

    
    public String getRoleValue() {
    	return this.role.getValue();
    }
    
    public void update(String passport, String birthdate, String firstname, String lastname, String username, String phone) {
    	this.passport=passport;
    	this.birthdate=birthdate;
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.username=username;
    	this.phone=phone;
    }
    @Builder
    public UserDAO(String name, String email, Role role){
        this.username = name;
        this.email = email;
        this.role = role;
    }
    
    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.passwd);
      }
    
    
}
