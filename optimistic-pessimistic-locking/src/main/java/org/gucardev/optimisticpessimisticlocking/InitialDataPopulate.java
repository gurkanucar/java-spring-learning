package org.gucardev.optimisticpessimisticlocking;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        var product = new Product();
        product.setName("product");
        product.setQuantity(25);
        productRepository.save(product);
    }
}
