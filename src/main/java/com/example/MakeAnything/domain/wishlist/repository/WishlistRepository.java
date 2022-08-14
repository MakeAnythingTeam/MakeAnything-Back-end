package com.example.MakeAnything.domain.wishlist.repository;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    public List<Wishlist> findAllByUserId(Long userId);

    public Wishlist findByUserAndModel(User user, Model model);
}
