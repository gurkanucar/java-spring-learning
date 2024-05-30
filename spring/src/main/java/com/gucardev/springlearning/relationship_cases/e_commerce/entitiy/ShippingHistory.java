package com.gucardev.springlearning.relationship_cases.e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class ShippingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // e.g., "Shipped", "In Transit", "Delivered"
    private LocalDateTime updateDate;
    private String carrier; // e.g., "UPS", "FedEx"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @Override
    public int hashCode() {
        return Objects.hash(id, status, carrier); // Avoid using order item
    }
}
