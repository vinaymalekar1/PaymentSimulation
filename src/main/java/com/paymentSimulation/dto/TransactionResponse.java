package com.paymentSimulation.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private String sender;
    private String receiver;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;
}
