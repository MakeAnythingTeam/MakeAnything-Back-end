package com.example.MakeAnything.domain.order.controller;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.order.service.OrderService;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderResponse;
import com.example.MakeAnything.domain.user.service.UserService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final JwtService jwtService;

    private IamportClient iamportClient = new IamportClient("3348224505374824" ,  "ntDy11qSIGlhQuhjSMXEYZv03fBa7X5H3OfWZl4XNxmIRNODO5MUYDHtdeqp7rXi3f2quwMuerjHuMav");

    @ResponseBody
    @PostMapping("/{modelId}")
    @ApiOperation(value = "결제 검증 후 주문 생성")
    public ApiResponse<CreateOrderResponse> createOrder(@PathVariable ("modelId") Long modelId, @RequestBody CreateOrderRequest createOrderRequest ) throws IamportResponseException, IOException {
        Long userId = jwtService.getUserId();

        BigDecimal serverAmount = iamportClient.paymentByImpUid(createOrderRequest.getImp_uid()).getResponse().getAmount();
        CreateOrderResponse createOrderResponse = orderService.createOrder(userId, modelId, createOrderRequest, serverAmount);

        return ApiResponse.success(createOrderResponse);
    }
}
