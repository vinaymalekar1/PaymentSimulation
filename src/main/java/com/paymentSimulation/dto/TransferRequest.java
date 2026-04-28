package com.paymentSimulation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private String receiverUsername;
    private Double amount;
}
