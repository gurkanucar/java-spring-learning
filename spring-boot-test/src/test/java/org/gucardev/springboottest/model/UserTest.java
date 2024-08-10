package org.gucardev.springboottest.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

  @Test
  void testUserGetterSetter() {
    User user = new User();
    Address address = new Address();

    List<Address> addresses = Collections.singletonList(address);

    user.setUsername("testUsername");
    user.setEmail("testEmail");
    user.setName("testName");
    user.setAddresses(addresses);

    assertEquals("testUsername", user.getUsername());
    assertEquals("testEmail", user.getEmail());
    assertEquals("testName", user.getName());
    assertEquals(addresses, user.getAddresses());
  }
}
