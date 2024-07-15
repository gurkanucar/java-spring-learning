package org.gucardev.chain_of_resp_validation_example.handlers;

import org.gucardev.chain_of_resp_validation_example.Handler;
import org.gucardev.chain_of_resp_validation_example.User;

public class CustomerExistsHandler extends Handler<User> {

  @Override
  public boolean handle(User user) {
    user.setUsername("update1");
    if (user.getId() == null) {
      System.out.println("customer does not exist!");
      return false;
    }
    System.out.println("CustomerExistsHandler success");
    return handleNext(user);
  }
}