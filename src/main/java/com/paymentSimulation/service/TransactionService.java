package com.paymentSimulation.service;


import com.paymentSimulation.dto.TransactionResponse;
import com.paymentSimulation.dto.TransferRequest;
import com.paymentSimulation.entity.Transaction;
import com.paymentSimulation.entity.User;
import com.paymentSimulation.entity.Wallet;
import com.paymentSimulation.repository.TransactionRepository;
import com.paymentSimulation.repository.UserRepository;
import com.paymentSimulation.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public String transfer(User sender, TransferRequest request) {

        User receiver = userRepository.findByUsername(request.getReceiverUsername())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Wallet senderWallet = walletRepository.findByUser(sender)
                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findByUser(receiver)
                .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

        if (senderWallet.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }


        senderWallet.setBalance(senderWallet.getBalance() - request.getAmount());
        receiverWallet.setBalance(receiverWallet.getBalance() + request.getAmount());

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);


        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(request.getAmount())
                .status("SUCCESS")
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return "Transfer successful";
    }

    public List<TransactionResponse> getTransactions(User user) {

        List<Transaction> sent = transactionRepository.findBySender(user);
        List<Transaction> received = transactionRepository.findByReceiver(user);

        return List.copyOf(
                List.of(sent, received)
                        .stream()
                        .flatMap(List::stream)
                        .map(tx -> TransactionResponse.builder()
                                .sender(tx.getSender().getUsername())
                                .receiver(tx.getReceiver().getUsername())
                                .amount(tx.getAmount())
                                .status(tx.getStatus())
                                .createdAt(tx.getCreatedAt())
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
