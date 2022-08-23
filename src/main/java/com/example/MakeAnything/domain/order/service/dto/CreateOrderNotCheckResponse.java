package com.example.MakeAnything.domain.order.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderNotCheckResponse {
    private Long orderId;
    private String resultMessage;

    @Builder
    public CreateOrderNotCheckResponse(Long orderId, String resultMessage) {
        this.orderId = orderId;
        this.resultMessage = resultMessage;
    }
}
