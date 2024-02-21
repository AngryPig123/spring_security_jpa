package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.example.jpa.constant.DeliveryStatus;
import org.spring.example.jpa.constant.DeliveryType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private DeliveryType deliveryType;  //  배송 타입

    @Enumerated(EnumType.STRING)
    @Column(name = "delivaery_statuc", nullable = false)
    private DeliveryStatus deliveryStatus;  //  배송 상태

    @OneToMany(mappedBy = "delivery")
    private List<OrderHeader> orderHeaders = new ArrayList<>();

    @Builder
    public Delivery(DeliveryType deliveryType, DeliveryStatus deliveryStatus) {
        this.deliveryType = deliveryType;
        this.deliveryStatus = deliveryStatus;
    }

    public void initDelivery() {
        this.deliveryType = DeliveryType.TYPE_A;
        this.deliveryStatus = DeliveryStatus.SEARCH;
    }

}
