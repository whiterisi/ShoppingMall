package com.asianaidt.shoppingmall.kakaopay;

import lombok.Data;

@Data
public class AmountDTO {
    private Integer total, tax_free, vat, point, discount;
}
