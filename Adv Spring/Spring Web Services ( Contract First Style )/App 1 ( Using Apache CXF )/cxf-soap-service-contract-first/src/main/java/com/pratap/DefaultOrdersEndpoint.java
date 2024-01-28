package com.pratap;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratap.schema.order.OrderInquiryResponseType;
import com.pratap.schema.order.OrderInquiryType;
import com.pratap.service.orders.Orders;

@WebService(portName = "OrdersSOAP", serviceName = "Orders", 
	endpointInterface = "com.pratap.service.orders.Orders", 
	targetNamespace = "http://www.pratap.com/service/Orders/")
public class DefaultOrdersEndpoint implements Orders {

	@Autowired
	OrderService orderService;
	
	@Override
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {
		OrderInquiryResponseType response = orderService.processOrder(orderInquiry.getUniqueOrderId(),orderInquiry.getOrderQuantity() ,
					orderInquiry.getAccountId(), orderInquiry.getEan13());
		return response;
	}

}
