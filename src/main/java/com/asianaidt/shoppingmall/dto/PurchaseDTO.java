package com.asianaidt.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("PurchaseDTO")
public class PurchaseDTO {
    private int purchaseid; //구매목록 id
    private String purchasedate;
    private String flightid;
    private int userid;
    private String amount;
    private int productid;
    private String name;
    private String brand;
    private int category;
    private int price;
    private int cnt;
    private String size;
    private String image;
    private int status;

}
