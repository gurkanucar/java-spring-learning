package org.gucardev.fluentAndStepPattern;

import java.util.ArrayList;
import java.util.List;
import org.gucardev.fluentAndStepPattern.steps.AccountStep;
import org.gucardev.fluentAndStepPattern.steps.BuildStep;
import org.gucardev.fluentAndStepPattern.steps.DeliveryOptionStep;
import org.gucardev.fluentAndStepPattern.steps.DiscountStep;
import org.gucardev.fluentAndStepPattern.steps.PaymentStep;
import org.gucardev.fluentAndStepPattern.steps.ProductStep;
import org.gucardev.fluentAndStepPattern.steps.ShippingStep;

public class OrderBuilder
    implements AccountStep,
        ShippingStep,
        PaymentStep,
        ProductStep,
        DeliveryOptionStep,
        BuildStep,
        DiscountStep {
  private String accountEmail;
  private String shippingAddress;
  private String paymentMethod;
  private List<Product> products = new ArrayList<>();
  private DeliveryOption deliveryOption;
  private double discount;

  public static AccountStep builder() {
    return new OrderBuilder();
  }

  @Override
  public ShippingStep setAccount(String accountEmail) {
    this.accountEmail = accountEmail;
    return this;
  }

  @Override
  public PaymentStep setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  @Override
  public ProductStep setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  @Override
  public ProductStep addProduct(Product product) {
    this.products.add(product);
    return this;
  }

  @Override
  public DeliveryOptionStep setDiscount(double discount) {
    this.discount = discount;
    return this;
  }

  @Override
  public DiscountStep finishProducts() {
    return this;
  }

  @Override
  public BuildStep setDeliveryOption(DeliveryOption deliveryOption) {
    this.deliveryOption = deliveryOption;
    return this;
  }

  @Override
  public Order build() {
    return new Order(
        accountEmail, shippingAddress, paymentMethod, products, deliveryOption, discount);
  }
}
