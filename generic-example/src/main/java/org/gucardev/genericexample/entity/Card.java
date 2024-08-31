package org.gucardev.genericexample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

