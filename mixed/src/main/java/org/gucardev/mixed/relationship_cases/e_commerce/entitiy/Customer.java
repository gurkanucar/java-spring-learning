package org.gucardev.mixed.relationship_cases.e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();


    @Override
    public int hashCode() {
        return Objects.hash(id, name); // Avoid using orders or reviews
    }
}
