package com.cravesphere.payment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	
	private int orderId;
    private int userId;
    private Double amount;
    private String status;
    private String transactionId;
    private LocalDateTime paymentTime;

}
