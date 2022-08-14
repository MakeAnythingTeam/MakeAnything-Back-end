package com.example.MakeAnything.domain.wishlist.service;

import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import com.example.MakeAnything.domain.wishlist.model.Wishlist;
import com.example.MakeAnything.domain.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    @Override
    public void toggleModelWish(Long userId, Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_MODEL));

        User user = userRepository.getReferenceById(userId);

        Wishlist wishlist = wishlistRepository.findByUserAndModel(user, model);

        if (wishlist == null) {
            wishlistRepository.save(Wishlist.of(user, model));
        } else {
            wishlistRepository.delete(wishlist);
        }
    }
}
