package com.paymentSimulation.controller;


import com.paymentSimulation.dto.TransactionResponse;
import com.paymentSimulation.dto.TransferRequest;
import com.paymentSimulation.entity.User;
import com.paymentSimulation.service.TransactionService;
import com.paymentSimulation.service.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final UserServiceHelper userServiceHelper;

    @PostMapping("/transfer")
    public String transfer(
            @RequestHeader("username") String username,
            @RequestBody TransferRequest request
    ) {

        User sender = userServiceHelper.getUser(username);
        return transactionService.transfer(sender, request);
    }

    @GetMapping("/history")
    public List<TransactionResponse> getHistory(
            @RequestHeader("username") String username
    ) {

        User user = userServiceHelper.getUser(username);
        return transactionService.getTransactions(user);
    }
}
