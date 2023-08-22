package com.asianaidt.shoppingmall.mapper;

import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import com.asianaidt.shoppingmall.dao.PurchaseProductDAO;
import com.asianaidt.shoppingmall.dto.PurchaseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PurchaseMapper {
    public List<PurchaseDTO> findAllPurchase(int userid) throws Exception;
    public List<PurchaseDAO> findSimplePurchase(int id) throws Exception;
    public List<PurchaseDTO> findPurchaseById(int purchaseid) throws  Exception;
    public List<PurchaseDTO> findPurchaseByFlightId(int flightid) throws  Exception;
    public PurchaseDAO findOne(int purchaseid) throws Exception;
    public int addPurchase(PurchaseDAO dao) throws Exception;
    public int addPurchaseProduct(List<PurchaseProductDAO> list) throws Exception;
    public int updateAlcohol( Map<String, Integer> map) throws Exception;
}
