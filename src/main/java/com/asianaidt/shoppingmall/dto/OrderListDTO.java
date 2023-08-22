package com.asianaidt.shoppingmall.dto;

import com.asianaidt.shoppingmall.dao.PurchaseDAO;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
	private PurchaseDAO purchaseDao;
	private List<PurchaseDTO> list;
}
