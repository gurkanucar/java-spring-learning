package org.gucardev.springboottest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {
  @Test
  void testAddressGetterSetter() {
    User user = new User();
    user.setUsername("testUsername");
    user.setEmail("testEmail");
    user.setName("testName");

    Address address = new Address();
    address.setTitle("Test title");
    address.setDetail("Test detail");
    address.setUser(user);

    assertEquals("Test title", address.getTitle());
    assertEquals("Test detail", address.getDetail());
    assertEquals(user, address.getUser());
  }
}
