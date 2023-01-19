package com.example.MakeAnything.domain.user.controller;

import com.example.MakeAnything.config.resolver.UserId;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.user.service.UserService;
import com.example.MakeAnything.domain.user.service.dto.BuyModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.SellModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.WishModelsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/sell")
    public ApiResponse<List<SellModelsResponse>> getSellModels(@UserId Long userId) {
        return ApiResponse.success(userService.getSellModels(userId));
    }

    @GetMapping("/users/buy")
    public ApiResponse<List<BuyModelsResponse>> getBuyModels(@UserId Long userId) {
        return ApiResponse.success(userService.getBuyModels(userId));
    }

    @GetMapping("/users/wish")
    public ApiResponse<List<WishModelsResponse>> getWishModels(@UserId Long userId) {
        return ApiResponse.success(userService.getWishModels(userId));
    }

}
