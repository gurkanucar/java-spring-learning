package org.gucardev.fluentAndStepPattern.steps;

import org.gucardev.fluentAndStepPattern.Product;

public interface ProductStep {

  ProductStep addProduct(Product product);

  DiscountStep finishProducts();
}
