package com.asianaidt.shoppingmall.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("PurchaseProductDAO")
public class  PurchaseProductDAO {
    private int cpid;
    private int purchaseid;
    private int productid;
    private int cnt;
    private String size;

    public PurchaseProductDAO(int purchaseid, int productid){
        this.purchaseid=purchaseid;
        this.productid=productid;
    }
    public PurchaseProductDAO(int purchaseid, int productid, int cnt, String size){
        this.purchaseid=purchaseid;
        this.productid=productid;
        this.cnt=cnt;
        this.size=size;
    }
    public PurchaseProductDAO(int purchaseid, int productid, int cnt){
        this.purchaseid=purchaseid;
        this.productid=productid;
        this.cnt=cnt;
    }
}
