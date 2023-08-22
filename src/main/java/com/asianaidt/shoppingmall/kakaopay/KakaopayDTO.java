package com.asianaidt.shoppingmall.kakaopay;

import lombok.Data;

import java.util.Date;

@Data
public class KakaopayDTO {
    //response
    private String tid, next_redirect_pc_url;
    private Date created_at;
}
