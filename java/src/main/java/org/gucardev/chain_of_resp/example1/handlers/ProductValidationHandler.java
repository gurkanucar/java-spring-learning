package org.gucardev.chain_of_resp.example1.handlers;

import org.gucardev.chain_of_resp.example1.Handler;
import org.gucardev.chain_of_resp.example1.User;

public class ProductValidationHandler extends Handler {

  @Override
  public boolean handle(Object payload) {
    User user = (User) payload;
    user.setUsername(user.getUsername()+"update2");
    if (!customerHasProducts(user)) {
      System.out.println("customer has no products!");
      return false;
    }
    System.out.println("ProductValidationHandler success");
    return handleNext(payload);
  }

  public boolean customerHasProducts(User user) {
    return Math.random() > 0.5;
  }
}
