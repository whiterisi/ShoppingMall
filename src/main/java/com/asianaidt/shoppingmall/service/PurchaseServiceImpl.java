package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import com.asianaidt.shoppingmall.dao.PurchaseProductDAO;
import com.asianaidt.shoppingmall.dto.OrderDTO;
import com.asianaidt.shoppingmall.dto.OrderListDTO;
import com.asianaidt.shoppingmall.dto.PurchaseDTO;
import com.asianaidt.shoppingmall.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseMapper mapper;
    
    @Override
    public List<OrderListDTO> findAllPurchase(int userid) throws Exception {
    	List<PurchaseDAO> daoList= mapper.findSimplePurchase(userid);
    	List<OrderListDTO> list= new ArrayList<>();
    	for(PurchaseDAO dao : daoList) {
    		 List<PurchaseDTO> dtoList = mapper.findPurchaseById(dao.getPurchaseid());
    		 list.add(new OrderListDTO(dao, dtoList));
    	}
        return list;
    }

    @Override
    public List<PurchaseDAO> findSimplePurchase(int id) throws Exception {
        return mapper.findSimplePurchase(id);
    }

    @Transactional
    @Override
    public int addPurchase(PurchaseDAO purchaseDao, List<OrderDTO> list) throws Exception {
        int purchaseid = mapper.addPurchase(purchaseDao);
        List<PurchaseProductDAO> ppList = new ArrayList<>();
        for(OrderDTO dto : list){
            ppList.add(new PurchaseProductDAO(purchaseid, dto.getProductId(), dto.getAmount()));
        }
        return mapper.addPurchaseProduct(ppList);
    }

    @Override
    public List<PurchaseDTO> findPurchaseById(int purchaseid) throws Exception {
        return mapper.findPurchaseById(purchaseid);
    }

    @Override
    public List<PurchaseDTO> findPurchaseByFlightId(int flightid) throws Exception {
        return mapper.findPurchaseByFlightId(flightid);
    }

	@Override
	public PurchaseDAO findOne(int purchaseid) throws Exception {
		// TODO Auto-generated method stub
		return mapper.findOne(purchaseid);
	}

}
