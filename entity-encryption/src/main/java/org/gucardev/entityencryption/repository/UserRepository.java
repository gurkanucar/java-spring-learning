package org.gucardev.entityencryption.repository;

import org.gucardev.entityencryption.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.addresses")
    List<User> findAllWithAddresses();

}
