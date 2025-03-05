package com.cravesphere.payment.service;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cravesphere.payment.dto.PaymentDto;
import com.cravesphere.payment.entity.Payment;
import com.cravesphere.payment.exception.PaymentNotFoundException;
import com.cravesphere.payment.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${razorpay.api.key}")
    private String razorpayKey;

    @Value("${razorpay.api.secret}")
    private String razorpaySecret;


	@Override
	public String processPayment(PaymentDto paymentDto) throws RazorpayException {
		LOGGER.info("Processing payment");
		
		String userValidation = restTemplate.getForObject("http://USER-SERVICE/api/users/" + paymentDto.getUserId(), String.class);
        String orderValidation = restTemplate.getForObject("http://ORDER-SERVICE/api/orders/" + paymentDto.getOrderId(), String.class);

        if (userValidation == null || orderValidation == null) {
            throw new RuntimeException("Invalid User ID or Order ID");
        }
        
        RazorpayClient razorpay = new RazorpayClient(razorpayKey, razorpaySecret);
        
        int amountInPaise = (int) (paymentDto.getAmount() * 100);
        
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_rcpt_" + paymentDto.getOrderId());
        orderRequest.put("payment_capture", 1);
        
        Order order = razorpay.orders.create(orderRequest);
        
        Payment payment = new Payment();
        payment.setUserId(paymentDto.getUserId());
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentStatus("Created");
        payment.setTransactionId(order.get("id"));
        payment.setPaymentTime(LocalDateTime.now());
		
        paymentRepository.save(payment);
        
        return order.toString();
	}

	@Override
	public Payment getPaymentByOrderId(int orderId) {
		LOGGER.info("fetching payment details using orderId: " + orderId);
		
		return paymentRepository.findByOrderId(orderId)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found for Order ID: " + orderId));
	}

	@Override
	public Payment getPaymentByTransactionId(String transactionId) {
		LOGGER.info("fetching payment details using transactionId: " + transactionId);
		
		return paymentRepository.findByTransactionId(transactionId)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found for Transaction ID: " + transactionId));

	}	

}
