package org.gucardev.genericexample.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    private String name;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

}

