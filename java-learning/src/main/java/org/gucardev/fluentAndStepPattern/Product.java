package org.gucardev.fluentAndStepPattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
  private String name;
  private double price;
  private int quantity;

  public Product(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }
  @Override
  public String toString() {
    return "Product{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
