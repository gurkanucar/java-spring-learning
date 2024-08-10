package org.gucardev.optimisticpessimisticlocking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void decreaseQuantity(Long productId, int quantity) {
        var product = productRepository.findById(productId).get();
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    @Transactional
    public void decreaseQuantityWithLock(Long productId, int quantity) {
        var product = productRepository.findByIdWithLock(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
