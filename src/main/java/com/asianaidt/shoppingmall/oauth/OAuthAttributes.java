package com.asianaidt.shoppingmall.oauth;

import java.util.Map;
import com.asianaidt.shoppingmall.dao.UserDAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String username;
	private String email;
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,Map<String, Object> attributes) {
			return ofGoogle(userNameAttributeName, attributes);
	}
	
	private static OAuthAttributes ofNaver(String userNameAtrributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .username((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAtrributeName)
                .build();
    }
	
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .username((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
			 
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String username, String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.email = email;
    }

	public UserDAO toEntity() {
				return UserDAO.builder()
						.username(username)
						.email(email)
		                .role(Role.USER)
		                .build();
	}

}
