package com.example.MakeAnything.domain.wishlist.controller;

import com.example.MakeAnything.config.resolver.UserId;
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

    @PostMapping("/models/{modelId}/wish")
    public ApiResponse<Object> toggleModelWish(@PathVariable Long modelId, @UserId Long userId) {

        wishlistService.toggleModelWish(userId, modelId);

        return ApiResponse.success(null);
    }


}
