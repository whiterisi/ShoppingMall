package com.asianaidt.shoppingmall.oauth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.ObjectDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final HttpSession session;
	private final OAuthService userService;
	 
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		System.out.println("registrationId"+registrationId);

		/* OAuth2 로그인 진행시 키가 되는 필드 값 (PK) (구글의 기본 코드는 "sub") */
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
			.getUserInfoEndpoint().getUserNameAttributeName();
		System.out.println("userNameAttributeNAme"+userNameAttributeName);
		/* OAuth2UserService */
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		System.out.println(attributes.toString());
		System.out.println("-----");
		UserDAO user = null;
		try {
			user = saveOrUpdate(attributes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.setAttribute("user", user);
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleValue())),
			attributes.getAttributes(),attributes.getNameAttributeKey());
	}
	 
	/* 소셜로그인시 기존 회원이 존재하면 수정날짜 정보만 업데이트해 기존의 데이터는 그대로 보존 */
	private UserDAO saveOrUpdate(OAuthAttributes attributes) throws Exception {
		String  email= attributes.getEmail();
		UserDAO user = userService.findUserByEmail(email);
		if(user==null){
			user = UserDAO.builder()
					.email( email)
					.username(attributes.getUsername())
					.role(Role.USER)
					.build();
			int id = userService.signUpGoogle(user);
			user.setId(id);
		}
		return user;
	}
}
