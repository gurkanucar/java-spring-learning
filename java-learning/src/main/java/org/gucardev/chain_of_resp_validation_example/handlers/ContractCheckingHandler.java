package org.gucardev.chain_of_resp_validation_example.handlers;


import org.gucardev.chain_of_resp_validation_example.Handler;
import org.gucardev.chain_of_resp_validation_example.User;

public class ContractCheckingHandler extends Handler<User> {

  @Override
  public boolean handle(User user) {
    user.setUsername(user.getUsername() + "update3");
    if (!areContractsValid(user)) {
      System.out.println("customer has no valid contracts!");
      return false;
    }
    System.out.println("ContractCheckingHandler success");
    return handleNext(user);
  }

  private boolean areContractsValid(User user) {
    return Math.random() > 0.5;
  }
}

