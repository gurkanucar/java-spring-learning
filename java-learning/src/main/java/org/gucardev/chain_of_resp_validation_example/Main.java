package org.gucardev.chain_of_resp_validation_example;


import org.gucardev.chain_of_resp_validation_example.handlers.ContractCheckingHandler;
import org.gucardev.chain_of_resp_validation_example.handlers.CustomerExistsHandler;
import org.gucardev.chain_of_resp_validation_example.handlers.ProductValidationHandler;

public class Main {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setUsername("grkn");
        user.setPassword("pass");

        Handler<User> handlerChain =
                new CustomerExistsHandler()
                        .setNextHandler(new ProductValidationHandler())
                        .setNextHandler(new ContractCheckingHandler());

        boolean result = handlerChain.handle(user);
        System.out.println("Handler chain completed with result: " + result);
        System.out.println("Final username: " + user.getUsername());
    }
}
