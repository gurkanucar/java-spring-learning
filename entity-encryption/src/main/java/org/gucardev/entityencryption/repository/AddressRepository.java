package org.gucardev.entityencryption.repository;


import org.gucardev.entityencryption.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

