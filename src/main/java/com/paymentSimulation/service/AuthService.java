package com.paymentSimulation.service;


import com.paymentSimulation.dto.AuthRequest;
import com.paymentSimulation.entity.User;
import com.paymentSimulation.entity.Wallet;
import com.paymentSimulation.repository.UserRepository;
import com.paymentSimulation.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public String register(AuthRequest request) {

        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new RuntimeException("Username already exists");
                });

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        Wallet wallet = Wallet.builder()
                .user(user)
                .balance(1000.0)
                .build();

        walletRepository.save(wallet);

        return "User registered successfully";
    }

    public User login(AuthRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
