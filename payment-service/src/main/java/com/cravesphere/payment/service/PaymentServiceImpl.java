package com.cravesphere.payment.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cravesphere.payment.dto.PaymentDto;
import com.cravesphere.payment.entity.Payment;
import com.cravesphere.payment.exception.PaymentNotFoundException;
import com.cravesphere.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Payment processPayment(PaymentDto paymentDto) {
		LOGGER.info("Processing payment");
		
		String userValidation = restTemplate.getForObject("http://USER-SERVICE/api/users/" + paymentDto.getUserId(), String.class);
        String orderValidation = restTemplate.getForObject("http://ORDER-SERVICE/api/orders/" + paymentDto.getOrderId(), String.class);

        if (userValidation == null || orderValidation == null) {
            throw new RuntimeException("Invalid User ID or Order ID");
        }
        
        String transactionId = UUID.randomUUID().toString();
        
        Payment payment = new Payment();
        payment.setUserId(paymentDto.getUserId());
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentStatus("Success");
        payment.setTransactionId(transactionId);
        payment.setPaymentTime(LocalDateTime.now());
		
        return paymentRepository.save(payment);
		
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
