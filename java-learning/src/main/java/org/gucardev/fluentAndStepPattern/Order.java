package org.gucardev.fluentAndStepPattern;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
  private String accountEmail;
  private String shippingAddress;
  private String paymentMethod;
  private List<Product> products;
  private DeliveryOption deliveryOption;
  private double discount;

  public Order(
      String accountEmail,
      String shippingAddress,
      String paymentMethod,
      List<Product> products,
      DeliveryOption deliveryOption,
      double discount) {
    this.accountEmail = accountEmail;
    this.shippingAddress = shippingAddress;
    this.paymentMethod = paymentMethod;
    this.products = products;
    this.deliveryOption = deliveryOption;
    this.discount = discount;
  }

  @Override
  public String toString() {
    return "Order{"
        + "accountEmail='"
        + accountEmail
        + '\n'
        + ", shippingAddress='"
        + shippingAddress
        + '\n'
        + ", paymentMethod='"
        + paymentMethod
        + '\n'
        + ", products="
        + products
        + ", discount='"
        + discount
        + '\n'
        + ", deliveryOption="
        + deliveryOption
        + '}';
  }
}
