package com.cravesphere.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cravesphere.payment.dto.PaymentDto;
import com.cravesphere.payment.entity.Payment;
import com.cravesphere.payment.service.PaymentService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping()
	public ResponseEntity<String> processPayment(@RequestBody PaymentDto paymentDto) throws RazorpayException {
		LOGGER.info("Received request to process payment");
		
		return ResponseEntity.ok(paymentService.processPayment(paymentDto));
	}
	
	@GetMapping("order/{id}")
	public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable("id") int orderId) {
		LOGGER.info("Recevied request to get payment details using orderId: " + orderId);
		
		return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
	}
	
	@GetMapping("transaction/{id}")
	public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable("id") String transactionId) {
		LOGGER.info("Recevied request to get payment details using transactionId: " + transactionId);
		
		return ResponseEntity.ok(paymentService.getPaymentByTransactionId(transactionId));
	}
	
}
