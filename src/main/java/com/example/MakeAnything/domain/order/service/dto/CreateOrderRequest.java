package com.example.MakeAnything.domain.order.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {

    private String imp_uid;
    private String merchant_uid;
}
