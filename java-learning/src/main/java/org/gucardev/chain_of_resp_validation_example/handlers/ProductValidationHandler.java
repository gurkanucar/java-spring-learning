package org.gucardev.chain_of_resp_validation_example.handlers;


import org.gucardev.chain_of_resp_validation_example.Handler;
import org.gucardev.chain_of_resp_validation_example.User;

public class ProductValidationHandler extends Handler<User> {

  @Override
  public boolean handle(User user) {
    user.setUsername(user.getUsername() + "update2");
    if (!customerHasProducts(user)) {
      System.out.println("customer has no products!");
      return false;
    }
    System.out.println("ProductValidationHandler success");
    return handleNext(user);
  }

  private boolean customerHasProducts(User user) {
    return Math.random() > 0.5;
  }
}