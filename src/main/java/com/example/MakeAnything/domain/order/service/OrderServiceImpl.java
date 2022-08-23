package com.example.MakeAnything.domain.order.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.order.model.Order;
import com.example.MakeAnything.domain.order.repository.OrderRepository;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckResponse;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderResponse;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ModelRepository modelRepository;

    @Transactional
    @Override
    public CreateOrderResponse createOrder(Long userId, Long modelId, CreateOrderRequest createOrderRequest, BigDecimal serverAmount) {
        Model model = modelRepository.findModelById(modelId);
        User user = userRepository.findUserById(userId);


        // 결제한 금액과 모델의 금액이 일치하지 않는 경우 -> 클라이언트가 스크립트를 조작하여 결제한 경우!
        if (serverAmount.longValue() != model.getPrice()) {
            CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                    .orderId(null)
                    .resultMessage("fail")
                    .build();

            return createOrderResponse;
        }

        Order order = Order.builder()
                .amount(model.getPrice())
                .imp_uid(createOrderRequest.getImp_uid())
                .merchant_uid(createOrderRequest.getMerchant_uid())
                .model(model)
                .user(user)
                .build();

        Order saveOrder = orderRepository.save(order);

        CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                .orderId(saveOrder.getId())
                .resultMessage("success")
                .build();

        return createOrderResponse;
    }

    @Transactional
    @Override
    public CreateOrderNotCheckResponse createOrderNotCheck(Long userId, Long modelId, CreateOrderNotCheckRequest createOrderNotCheckRequest) {

        User user = userRepository.findUserById(userId);
        Model model = modelRepository.findModelById(modelId);

        Order order = Order.builder()
                .amount(model.getPrice())
                .imp_uid(createOrderNotCheckRequest.getImp_uid())
                .merchant_uid(createOrderNotCheckRequest.getMerchant_uid())
                .model(model)
                .user(user)
                .build();

        Order saveOrder = orderRepository.save(order);

        CreateOrderNotCheckResponse createOrderNotCheckResponse = CreateOrderNotCheckResponse.builder().
                orderId(saveOrder.getId())
                .resultMessage("success")
                .build();

        return createOrderNotCheckResponse;
    }
}
