package com.cravesphere.payment.service;

import com.cravesphere.payment.dto.PaymentDto;
import com.cravesphere.payment.entity.Payment;
import com.razorpay.RazorpayException;

public interface PaymentService {
	
	String processPayment(PaymentDto paymentDto) throws RazorpayException;
	
	Payment getPaymentByOrderId(int orderId);
	
	Payment getPaymentByTransactionId(String transactionId);

}
