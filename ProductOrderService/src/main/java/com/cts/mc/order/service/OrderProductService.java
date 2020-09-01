package com.cts.mc.order.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.mc.order.dao.OrderRepository;
import com.cts.mc.order.exception.OrderException;
import com.cts.mc.order.model.EmailMessage;
import com.cts.mc.order.model.Order;

@Service
public class OrderProductService implements IOrderProductService {
	static Logger log = Logger.getLogger(OrderProductService.class.getName());
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order addProductToCart(Order order) throws OrderException {

		Order orderInCart = null ;
			orderInCart=orderRepository.save(order);
		return orderInCart;
	}

	@Override
	public List<Order> listCartProducts() throws OrderException {
		// TODO Auto-generated method stub
		List<Order> cartProducts =(List<Order>) orderRepository.findAll();
		return cartProducts;
	}

	@Override
	public String checkoutOrders(int orderId) throws OrderException {
		List<Order> orders = listCartProducts();
		orders.forEach(order->{
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setTo_address("ybaburao@gmail.com");
			emailMessage.setOrderId(order.getOrderId());
			emailMessage.setBody("Your order for the Order id: "+orderId+ " is confirmed");
			restTemplate.getForObject("https://emailconfirmationservice/email/send",EmailMessage.class,emailMessage);
		});
		
		return "Your Order Confirmed";
	}
	

}
