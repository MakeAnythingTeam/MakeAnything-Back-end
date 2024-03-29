package com.example.MakeAnything.domain.order.repository;

import com.example.MakeAnything.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(Long userId);

    Order findOrderByUserIdAndModelId(Long userId, Long modelId);
}