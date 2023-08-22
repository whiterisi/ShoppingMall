package com.asianaidt.shoppingmall.kakaopay;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class KakaoPayService {

    private static final String HOST = "https://kapi.kakao.com";
    private static KakaopayDTO dto;
    private static KakaoPayApprovaDTO payApprovaDTO;

    public static  String kakaoPayReady(HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "d7f99300dcbc3fc68ca1cda53f0624cb");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String quantity = String.valueOf(session.getAttribute("cnt"));
        String intWon = String.valueOf(session.getAttribute("intWon"));  
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "아시아나");
        params.add("item_name", "아시아나 기내 면세품");
        params.add("quantity", quantity);
        params.add("total_amount", intWon);
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/api/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/cartList");
        params.add("fail_url", "http://localhost:8080/cartList");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            dto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaopayDTO.class);
            return dto.getNext_redirect_pc_url();
        }  catch(Exception e){
            e.printStackTrace();
        }
        return "/purchase";
    }

    public KakaoPayApprovaDTO kakaoPayInfo(String pg_token, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "d7f99300dcbc3fc68ca1cda53f0624cb");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        String intWon = String.valueOf(session.getAttribute("intWon"));     

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", dto.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "아시아나");
        params.add("pg_token", pg_token);
        params.add("total_amount", intWon);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            payApprovaDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovaDTO.class);
            return payApprovaDTO;
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
