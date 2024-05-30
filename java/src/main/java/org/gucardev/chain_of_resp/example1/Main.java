package org.gucardev.chain_of_resp.example1;

import org.gucardev.chain_of_resp.example1.handlers.ContractCheckingHandler;
import org.gucardev.chain_of_resp.example1.handlers.CustomerExistsHandler;
import org.gucardev.chain_of_resp.example1.handlers.ProductValidationHandler;

public class Main {

  public static void main(String[] args) {

    User user = new User();
    user.setId(1L);
    user.setUsername("grkn");
    user.setPassword("pass");

    Handler handler =
        new CustomerExistsHandler()
            .setNextHandler(new ProductValidationHandler())
            .setNextHandler(new ContractCheckingHandler());

    System.out.println(handler.handle(user));

    System.out.println(user.getUsername());
  }
}
