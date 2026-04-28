package com.paymentSimulation.repository;

import com.paymentSimulation.entity.Wallet;
import com.paymentSimulation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);
}