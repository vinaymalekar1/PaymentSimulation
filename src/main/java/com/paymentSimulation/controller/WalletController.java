package com.paymentSimulation.controller;


import com.paymentSimulation.entity.User;
import com.paymentSimulation.service.UserServiceHelper;
import com.paymentSimulation.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final UserServiceHelper userServiceHelper;

    @GetMapping("/balance")
    public Double getBalance(@RequestHeader("username") String username) {

        User user = userServiceHelper.getUser(username);
        return walletService.getBalance(user);
    }
}
