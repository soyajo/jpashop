package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long Id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // 무조건 spring 사용
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; // READY, COMP

}
