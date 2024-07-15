package org.gucardev.fluentAndStepPattern;

public class Main {

  public static void main(String[] args) {
    Order order =
        OrderBuilder.builder()
            .setAccount("email@example.com")
            .setShippingAddress("123 Street, City, State, Country")
            .setPaymentMethod("Credit Card")
            .addProduct(new Product("powerbank", 500, 1))
            .addProduct(new Product("coffee", 20, 2))
            .finishProducts()
            .setDiscount(120.9)
            .setDeliveryOption(DeliveryOption.STANDARD)
            .build();

    System.out.println(order);
  }
}
