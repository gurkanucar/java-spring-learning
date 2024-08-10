package org.gucardev.optimisticpessimisticlocking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository mockProductRepository;

    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService(mockProductRepository);
    }

    @Test
    void testDecreaseQuantity() {
        // Setup
        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        product1.setQuantity(0);
        product1.setVersion(0L);
        final Optional<Product> product = Optional.of(product1);
        when(mockProductRepository.findById(0L)).thenReturn(product);

        // Run the test
        productServiceUnderTest.decreaseQuantity(0L, 0);

        // Verify the results
        verify(mockProductRepository).save(any(Product.class));
    }

    @Test
    void testDecreaseQuantity_ProductRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockProductRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> productServiceUnderTest.decreaseQuantity(0L, 0))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testDecreaseQuantityWithLock() {
        // Setup
        // Configure ProductRepository.findByIdWithLock(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        product1.setQuantity(0);
        product1.setVersion(0L);
        final Optional<Product> product = Optional.of(product1);
        when(mockProductRepository.findByIdWithLock(0L)).thenReturn(product);

        // Run the test
        productServiceUnderTest.decreaseQuantityWithLock(0L, 0);

        // Verify the results
        verify(mockProductRepository).save(any(Product.class));
    }

    @Test
    void testDecreaseQuantityWithLock_ProductRepositoryFindByIdWithLockReturnsAbsent() {
        // Setup
        when(mockProductRepository.findByIdWithLock(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> productServiceUnderTest.decreaseQuantityWithLock(0L, 0))
                .isInstanceOf(RuntimeException.class);
    }
}
