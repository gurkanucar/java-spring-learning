package org.gucardev.chain_of_resp.example1.handlers;

import org.gucardev.chain_of_resp.example1.Handler;
import org.gucardev.chain_of_resp.example1.User;

public class ContractCheckingHandler extends Handler {

  @Override
  public boolean handle(Object payload) {
    User user = (User) payload;
    user.setUsername(user.getUsername() + "update3");
    if (!areContractsValid(user)) {
      System.out.println("customer has no valid contracts!");
      return false;
    }
    System.out.println("ContractCheckingHandler success");
    return handleNext(payload);
  }

  public boolean areContractsValid(User user) {
    return Math.random() > 0.5;
  }
}
