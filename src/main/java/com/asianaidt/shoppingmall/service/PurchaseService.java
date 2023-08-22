package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dao.CartDAO;
import com.asianaidt.shoppingmall.dao.ProductDAO;
import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;
import com.asianaidt.shoppingmall.dto.OrderListDTO;
import com.asianaidt.shoppingmall.dto.PurchaseDTO;

import java.util.List;


public interface PurchaseService {
	public List<OrderListDTO> findAllPurchase(int userid) throws Exception;
    public List<PurchaseDAO> findSimplePurchase(int id) throws Exception;
    public int addPurchase(PurchaseDAO purchaseDao, List<OrderDTO> list) throws Exception;
    public List<PurchaseDTO> findPurchaseById(int purchaseid) throws  Exception;
    public List<PurchaseDTO> findPurchaseByFlightId(int flightid) throws  Exception;
    public PurchaseDAO findOne(int purchaseid) throws Exception;
}
