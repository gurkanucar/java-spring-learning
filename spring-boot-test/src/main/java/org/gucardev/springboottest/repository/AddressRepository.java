package org.gucardev.springboottest.repository;

import org.gucardev.springboottest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findAllByUser_Id(Long id);
}
