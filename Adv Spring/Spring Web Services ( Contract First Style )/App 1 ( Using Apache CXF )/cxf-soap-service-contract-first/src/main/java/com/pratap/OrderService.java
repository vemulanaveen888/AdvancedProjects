package com.pratap;

import com.pratap.schema.order.OrderInquiryResponseType;

public interface OrderService {
	
	public OrderInquiryResponseType processOrder(int uniqueOrderId,
				int orderQuantity, int accountId , long en13);

}
