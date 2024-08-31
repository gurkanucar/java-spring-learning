package org.gucardev.genericexample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();

}

