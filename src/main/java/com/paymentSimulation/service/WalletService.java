package com.paymentSimulation.service;


import com.paymentSimulation.entity.User;
import com.paymentSimulation.entity.Wallet;
import com.paymentSimulation.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Double getBalance(User user) {

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return wallet.getBalance();
    }
}