package com.paymentSimulation.repository;

import com.paymentSimulation.entity.Transaction;
import com.paymentSimulation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySender(User sender);

    List<Transaction> findByReceiver(User receiver);
}
