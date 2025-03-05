package com.cravesphere.payment.service;

import com.cravesphere.payment.dto.PaymentDto;
import com.cravesphere.payment.entity.Payment;

public interface PaymentService {
	
	Payment processPayment(PaymentDto paymentDto);
	
	Payment getPaymentByOrderId(int orderId);
	
	Payment getPaymentByTransactionId(String transactionId);

}
