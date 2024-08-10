package org.gucardev.springboottest.repository;

import org.gucardev.springboottest.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("classpath:db/addressData.sql")
class AddressRepositoryTest {

  @Autowired private AddressRepository addressRepository;

  @Test
  void testFindAll() {
    List<Address> addresses = addressRepository.findAll();
    assertThat(addresses).hasSize(3);
  }

  @Test
  void testFindAllByUserId() {
    List<Address> addresses = addressRepository.findAllByUser_Id(1L);
    assertThat(addresses).hasSize(2);
  }

  @Test
  void testFindById() {
    Optional<Address> address = addressRepository.findById(1L);
    assertThat(address).isPresent();
    assertThat(address.get().getTitle()).isEqualTo("Home");
  }
}
