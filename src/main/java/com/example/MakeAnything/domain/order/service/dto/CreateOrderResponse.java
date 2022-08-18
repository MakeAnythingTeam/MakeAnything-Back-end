package com.example.MakeAnything.domain.order.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderResponse {
    private Long orderId;
    private String resultMessage;

    @Builder
    public CreateOrderResponse(Long orderId, String resultMessage) {
        this.orderId = orderId;
        this.resultMessage = resultMessage;
    }
}