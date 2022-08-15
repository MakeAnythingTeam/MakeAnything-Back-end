package com.example.MakeAnything.domain.user.service;

import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.order.repository.OrderRepository;
import com.example.MakeAnything.domain.user.service.dto.BuyModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.SellModelsResponse;
import com.example.MakeAnything.domain.user.service.dto.WishModelsResponse;
import com.example.MakeAnything.domain.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WishlistRepository wishlistRepository;

    private final ModelRepository modelRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<SellModelsResponse> getSellModels(Long userId) {
        return modelRepository.findAllByUserId(userId).stream()
                .map(model -> SellModelsResponse.of(model))
                .collect(Collectors.toList());
    }

    @Override
    public List<BuyModelsResponse> getBuyModels(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(order -> BuyModelsResponse.of(order.getModel()))
                .collect(Collectors.toList());
    }


    @Override
    public List<WishModelsResponse> getWishModels(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream()
                .map(wishlist -> WishModelsResponse.of(wishlist.getModel()))
                .collect(Collectors.toList());
    }
}
