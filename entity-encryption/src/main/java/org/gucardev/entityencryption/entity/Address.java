package org.gucardev.entityencryption.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.gucardev.entityencryption.config.Encrypt;

@Entity
@Getter
@Setter
public class Address extends BaseEntity {

    @Convert(converter = Encrypt.class)
    private String street;

    @Convert(converter = Encrypt.class)
    private String city;

    @Convert(converter = Encrypt.class)
    private String country;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}