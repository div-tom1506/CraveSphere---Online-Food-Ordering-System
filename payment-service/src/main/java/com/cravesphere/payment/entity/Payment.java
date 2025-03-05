package com.cravesphere.payment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private int orderId;
    private int userId;
    private Double amount;
    private String paymentStatus;
    private String transactionId;
    private LocalDateTime paymentTime;

}
