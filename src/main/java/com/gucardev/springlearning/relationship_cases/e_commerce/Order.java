package com.gucardev.springlearning.relationship_cases.e_commerce;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new HashSet<>();
    @Override
    public int hashCode() {
        return Objects.hash(id); // Avoid using customer or orderItems
    }
}
