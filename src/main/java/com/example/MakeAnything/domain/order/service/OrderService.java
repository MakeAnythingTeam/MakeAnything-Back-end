package com.example.MakeAnything.domain.order.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckResponse;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderResponse;
import com.example.MakeAnything.domain.user.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface OrderService {

    CreateOrderResponse createOrder(Long userId, Long modelId, CreateOrderRequest createOrderRequest, BigDecimal serverAmount);

    CreateOrderNotCheckResponse createOrderNotCheck(Long userId, Long modelId, CreateOrderNotCheckRequest createOrderNotCheckRequest);
}
