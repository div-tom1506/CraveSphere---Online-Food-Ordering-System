package com.cravesphere.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cravesphere.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	Optional<Payment> findByOrderId(int orderId);  
	
    Optional<Payment> findByTransactionId(String transactionId);

}
