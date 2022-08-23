package com.example.MakeAnything.domain.order.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderNotCheckRequest {
    private String imp_uid;
    private String merchant_uid;
}
