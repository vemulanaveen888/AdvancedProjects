package com.pratap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.pratap.schema.order.AccountType;
import com.pratap.schema.order.BookType;
import com.pratap.schema.order.ObjectFactory;
import com.pratap.schema.order.OrderInquiryResponseType;
import com.pratap.schema.order.OrderItemType;
import com.pratap.schema.order.OrderStatusType;
import com.pratap.schema.order.OrderType;

@Service
public class DefaultOrderService implements OrderService {

	@Override
	public OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long en13) {
		ObjectFactory factory = new ObjectFactory();
		OrderInquiryResponseType response = factory.createOrderInquiryResponseType();

		AccountType account = factory.createAccountType();
		account.setAccountId(accountId);
		response.setAccount(account);

		OrderItemType orderItem = factory.createOrderItemType();
		BookType book = factory.createBookType();
		book.setEan13(en13);
		book.setTitle("A CXF Book");
		orderItem.setBook(book);

		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date(System.currentTimeMillis()));

			XMLGregorianCalendar estimatedShippingDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			orderItem.setExpectedShippingDate(estimatedShippingDate);
			orderItem.setLineNumber(Integer.valueOf(1));
			orderItem.setPrice(new BigDecimal(5));
			orderItem.setQuantityShipped(orderQuantity);
		} catch (Exception e) {

		}

		OrderType order = factory.createOrderType();
		order.setOrderStatus(OrderStatusType.READY);
		order.getOrderItems().add(orderItem);
		response.setOrder(order);

		return response;
	}

}
