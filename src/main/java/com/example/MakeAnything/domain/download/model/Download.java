package com.example.MakeAnything.domain.download.model;

import com.example.MakeAnything.domain.order.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean downloadStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @Builder
    public Download(Boolean downloadStatus, Order order) {
        this.downloadStatus = downloadStatus;
        this.order = order;
    }

    public void downloadDone() {
        this.downloadStatus = true;
    }
}