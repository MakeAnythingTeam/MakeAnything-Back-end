package com.example.MakeAnything.domain.wishlist.controller;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final JwtService jwtService;

    @PostMapping("/models/{modelId}/wish")
    public ApiResponse<Object> toggleModelWish(@PathVariable Long modelId) {
        Long userId = jwtService.getUserId();

        wishlistService.toggleModelWish(userId, modelId);

        return ApiResponse.success(null);
    }


}
