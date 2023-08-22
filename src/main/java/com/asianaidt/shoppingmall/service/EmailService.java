package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dao.UserDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(UserDAO user, String randomPassword){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom("rnxaxrx@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername(), "UTF-8"));
            message.setSubject("[아시아나 기내 면세점] " + user.getUsername() + "님 임시비밀번호 발급 메일");

            String mailForm = "<div style='width:960px; margin:0 auto;'>";
            mailForm += "<table  cellpadding='0' cellspacing='0' border='0' width='960' align='left' valign='middle' style='margin:0 auto; padding:0; min-width:960px; border:1px solid #ebebeb; font-family:'맑은 고딕', 'Malgun Gothic', '돋움', Dotum, sans-serif; font-size:18px; color:#666666; letter-spacing:-1.3px; line-height:1.8;'>";
            mailForm += "<tbody>";
            mailForm += "<tr>";
            mailForm += "<td width='70' height='80'></td>";
            mailForm += "<td width='820' height='80'></td>";
            mailForm += "<td width='70' height='80'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td height='30'></td>";
            mailForm += "<td height='30' align='right'>";
            mailForm += "</td>";
            mailForm += "<td height='30'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td height='30'></td>";
            mailForm += "<td height='30'></td>";
            mailForm += "<td height='30'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td></td>";
            mailForm += "<td>";
            mailForm += "<p style='font-size:40px; color:#333333; font-weight:300; line-height:1; letter-spacing:-4px;'>임시비밀번호 안내</p>";
            mailForm += "</td>";
            mailForm += "<td></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td height='30'></td>";
            mailForm += "<td height='30'></td>";
            mailForm += "<td height='30'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td></td>";
            mailForm += "<td>";
            mailForm += "<p style='font-size:16px;'>안녕하세요.<br/>" + user.getUsername() + "님이 요청하신 임시비밀번호를 안내해드립니다.<br/>해당 임시비밀번호로 로그인 후, 비밀번호를 변경해 주세요.</p>";
            mailForm += "</td>";
            mailForm += "<td></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td height='15'></td>";
            mailForm += "<td height='15'></td>";
            mailForm += "<td height='15'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td></td>";
            mailForm += "<td style='padding:30px; background:#f5f5f5; text-align:center;'>";
            mailForm += "<p style='font-size:25px; font-weight:500; color:#333333;'><span style='margin-right:20px; color:#666666;'>임시비밀번호 : </span>" + randomPassword + "</p>";
            mailForm += "</td>";
            mailForm += "<td></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td height='15'></td>";
            mailForm += "<td height='15'></td>";
            mailForm += "<td height='15'></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td></td>";
            mailForm += "<td>";
            mailForm += "<p style='font-size:16px;'>감사합니다.</p>";
            mailForm += "</td>";
            mailForm += "<td></td>";
            mailForm += "</tr>";
            mailForm += "<tr>";
            mailForm += "<td width='70' height='80'></td>";
            mailForm += "<td width='820' height='80'></td>";
            mailForm += "<td width='70' height='80'></td>";
            mailForm += "</tr>";
            mailForm += "</tbody>";
            mailForm += "</table>";
            mailForm += "</div>";
            message.setText(mailForm, "UTF-8", "html");
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendOrderMail(UserDAO user, List<OrderDTO> list, String sum, String purchasedate){
    	try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom("rnxaxrx@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername(), "UTF-8"));
            message.setSubject("[아시아나 기내 면세점] 주문 내역");

            String mailForm = "<div style='width:960px; margin:0 auto;'>";
            mailForm += "<table  cellpadding='0' cellspacing='0' border='0' width='960' align='left' valign='middle' style='margin:0 auto; padding:0; min-width:960px; border:1px solid #ebebeb; font-family:'맑은 고딕', 'Malgun Gothic', '돋움', Dotum, sans-serif; font-size:18px; color:#666666; letter-spacing:-1.3px; line-height:1.8;'>";
            mailForm += "<tbody><tr><td width='70' height='80'></td><td width='820' height='80'></td>";
            mailForm += "<td width='70' height='80'></td></tr><tr><td height='30'></td><td height='30'></td>";
            mailForm += "<td height='30'></td></tr><tr><td></td><td><p style='font-size:40px; color:#333333; font-weight:300; line-height:1; letter-spacing:-4px;'>주문 내역 안내</p>";
            mailForm += "</td><td></td></tr><tr><td height='30'></td><td height='30'></td><td height='30'></td></tr><tr><td></td><td>";
            mailForm += "<p style='font-size:16px;'>안녕하세요.<br/>아시아나에서 결제하신 주문내역을 알려드립니다.<br/></p>";
            mailForm += "</td><td></td> </tr><tr><td height='15'></td><td height='15'></td><td height='15'></td></tr><tr>";
            mailForm += "<td height='15'></td><td height='15'> 주문일자: "+purchasedate+"</td><td height='15'></td>";
            mailForm += "</tr><tr><td height='15'></td><td height='15'></td><td height='15'></td></tr><tr><td></td>";
            mailForm += "<td style='padding:40px; background:#f5f5f5; text-align:center;'>";
            mailForm += "<table  cellpadding='0' cellspacing='0' border='0'valign='middle' text-align:center style='margin:0 auto; padding:0; border:1px solid #ebebeb; font-family:'맑은 고딕', 'Malgun Gothic', '돋움', Dotum, sans-serif; font-size:18px; color:#666666; letter-spacing:-1.3px; line-height:1.8;'>";
            mailForm += "<thead><tr>";
            mailForm += "<th style ='padding: 5px;  width: 700px;'>상품 이름</th>";
            mailForm += "<th style ='padding: 5px; width: 100px;' >가격</th>";
            mailForm += "<th style ='padding: 5px;  width: 100px;'>수량</th>";
            mailForm += "</tr></thead><tbody>";           
	           for(OrderDTO dto: list) {
	               mailForm += "<tr><td style ='padding: 5px; text-align: left; width: 700px;'>"+dto.getName()+"</td>";
	               mailForm += "<td style ='padding: 5px; text-align: right; width: 100px;'>"+dto.getSalePrice()+"</td>";
	               mailForm += "<td style ='padding: 5px; text-align: right; width: 100px;'>"+dto.getAmount()+"</td></tr>";
	           }
            mailForm += "</tbody></table></td><td></td></tr><tr>";
            mailForm += "<td height='15'></td><td height='15'></td><td height='15'></td></tr><tr><td height='15'></td>";
            mailForm += "<td height='15'> 총 결제금액: "+sum+"원 </td> <td height='15'></td>";
            mailForm += "</tr><tr><td height='15'></td><td height='15'></td><td height='15'></td></tr><tr><td></td><td><p style='font-size:16px;'>감사합니다.</p></td><td></td></tr><tr>";
            mailForm += "<td width='70' height='80'></td><td width='820' height='80'></td><td width='70' height='80'></td></tr></tbody></table>";
            mailForm += "</div>";
            message.setText(mailForm, "UTF-8", "html");
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getRandomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

}

