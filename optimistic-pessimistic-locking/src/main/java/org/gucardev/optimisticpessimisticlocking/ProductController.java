package org.gucardev.optimisticpessimisticlocking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{productId}/{quantity}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable Long productId, @PathVariable Integer quantity) {
        productService.decreaseQuantity(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/plock/{productId}/{quantity}")
    public ResponseEntity<?> decreaseQuantityWithLock(@PathVariable Long productId, @PathVariable Integer quantity) {
        productService.decreaseQuantityWithLock(productId, quantity);
        return ResponseEntity.ok().build();
    }

}
