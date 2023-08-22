package com.asianaidt.shoppingmall.dao;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("PurchaseDAO")
public class PurchaseDAO {
    private int purchaseid;
    private int userid;
    private String flightid;
    private String purchasedate;
    private String amount;
    private int cnt;
    private int status;
    
    public PurchaseDAO(int userid, String flightid, String purchasedate, String amount, int cnt) {
    	this.userid = userid;
    	this.flightid=flightid;
    	this.purchasedate=purchasedate;
    	this.amount = amount;
    	this.cnt = cnt;
    	this.status = 0;
    }
}
