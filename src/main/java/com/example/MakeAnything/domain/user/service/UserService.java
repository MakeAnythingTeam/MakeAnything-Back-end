package com.example.MakeAnything.domain.user.service;

import com.example.MakeAnything.domain.user.service.dto.BuyModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.SellModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.WishModelsResponse;

import java.util.List;


public interface UserService {

    List<SellModelsResponse> getSellModels(Long userId);

    List<BuyModelsResponse> getBuyModels(Long userId);

    List<WishModelsResponse> getWishModels(Long userId);
}
