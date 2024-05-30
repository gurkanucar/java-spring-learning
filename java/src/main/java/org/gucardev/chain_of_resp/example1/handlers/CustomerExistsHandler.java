package org.gucardev.chain_of_resp.example1.handlers;

import org.gucardev.chain_of_resp.example1.Handler;
import org.gucardev.chain_of_resp.example1.User;

public class CustomerExistsHandler extends Handler {

  @Override
  public boolean handle(Object payload) {
    User user = (User) payload;
    user.setUsername("update1");
    if (user.getId() == null) {
      System.out.println("customer does not exist!");
      return false;
    }
    System.out.println("CustomerExistsHandler success");
    return handleNext(payload);
  }
}
