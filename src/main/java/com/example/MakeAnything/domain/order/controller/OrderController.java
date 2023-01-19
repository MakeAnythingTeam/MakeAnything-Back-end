package com.example.MakeAnything.domain.order.controller;

import com.example.MakeAnything.config.resolver.UserId;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.order.service.OrderService;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderNotCheckResponse;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderRequest;
import com.example.MakeAnything.domain.order.service.dto.CreateOrderResponse;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import io.swagger.annotations.Api;
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

    private IamportClient iamportClient = new IamportClient("3348224505374824" ,  "ntDy11qSIGlhQuhjSMXEYZv03fBa7X5H3OfWZl4XNxmIRNODO5MUYDHtdeqp7rXi3f2quwMuerjHuMav");

    @ResponseBody
    @PostMapping("/{modelId}")
    @ApiOperation(value = "결제 검증 후 주문 생성")
    public ApiResponse<CreateOrderResponse> createOrder(@PathVariable ("modelId") Long modelId,
                                                        @RequestBody CreateOrderRequest createOrderRequest,
                                                        @UserId Long userId) throws IamportResponseException, IOException {

        BigDecimal serverAmount = iamportClient.paymentByImpUid(createOrderRequest.getImp_uid()).getResponse().getAmount();
        CreateOrderResponse createOrderResponse = orderService.createOrder(userId, modelId, createOrderRequest, serverAmount);

        return ApiResponse.success(createOrderResponse);
    }

    @ResponseBody
    @PostMapping("/notcheck/{modelId}")
    @ApiOperation(value = "결제 검증 없이 주문 생성")
    public ApiResponse<CreateOrderNotCheckResponse> createOrderNotCheck(@PathVariable("modelId")Long modelId,
                                                                        @RequestBody CreateOrderNotCheckRequest createOrderNotCheckRequest,
                                                                        @UserId Long userId) {

        CreateOrderNotCheckResponse createOrderNotCheckResponse = orderService.createOrderNotCheck(userId, modelId, createOrderNotCheckRequest);
        return ApiResponse.success(createOrderNotCheckResponse);
    }
}
