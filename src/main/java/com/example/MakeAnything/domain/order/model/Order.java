package com.example.MakeAnything.domain.order.model;

import com.example.MakeAnything.domain.common.BaseTimeEntity;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelId")
    private Model model;

    private String imp_uid;
    private String merchant_uid;
    private Long amount;

    private Boolean downloadStatus;

    private LocalDateTime createdAt;

    private LocalDateTime canceledAt;

    @Builder
    public Order(User user, Model model, String imp_uid, String merchant_uid, Long amount) {
        this.user = user;
        this.model = model;
        this.imp_uid = imp_uid;
        this.merchant_uid = merchant_uid;
        this.amount = amount;
        this.downloadStatus = false;
        this.createdAt = LocalDateTime.now();
        this.canceledAt = null;
    }

    // 주문 취소 ( true 오면 주문 취소 가능, false 오면 주문 취소 불가능 )
    public boolean cancelOrder() {
        if (this.downloadStatus == false) {
            canceledAt = LocalDateTime.now();
            return true;
        } else {
            return false;
        }
    }
}
