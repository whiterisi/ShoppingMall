package com.asianaidt.shoppingmall.kakaopay;

import lombok.Data;

@Data
public class CardDTO {

    private String purchase_corp, purchase_corp_code;
    private String issuer_corp, issuer_corp_code;
    private String bin, card_type, install_month, approved_id, card_mid;
    private String interest_free_install, card_item_code;
}
