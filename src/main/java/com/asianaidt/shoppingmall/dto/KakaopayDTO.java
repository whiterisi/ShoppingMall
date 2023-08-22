package com.asianaidt.shoppingmall.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KakaopayDTO {

    private String tid, next_redirect_pc_url;
    private Date created_at;
}
