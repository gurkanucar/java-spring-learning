package com.gucardev.springlearning.relationship_cases.e_commerce;

import com.github.javafaker.Faker;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.*;
import com.gucardev.springlearning.relationship_cases.e_commerce.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class DataGeneratorConfig {

    @Bean
    @Transactional
    public CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                               CategoryRepository categoryRepository,
                                               OptionValueRepository optionValueRepository,
                                               MerchantRepository merchantRepository,
                                               CustomerRepository customerRepository,
                                               OrderRepository orderRepository,
                                               OrderItemRepository orderItemRepository,
                                               OptionTypeRepository optionTypeRepository,
                                               ShippingAddressRepository shippingAddressRepository,
                                               ShippingHistoryRepository shippingHistoryRepository,
                                               PaymentRepository paymentRepository,
                                               ReviewRepository reviewRepository) {
        return args -> {
            createInitData(productRepository, categoryRepository, optionValueRepository, merchantRepository,
                    customerRepository, orderRepository, orderItemRepository, optionTypeRepository,
                    shippingAddressRepository, shippingHistoryRepository, paymentRepository, reviewRepository
            );
        };
    }

    void createInitData(ProductRepository productRepository,
                        CategoryRepository categoryRepository,
                        OptionValueRepository optionValueRepository,
                        MerchantRepository merchantRepository,
                        CustomerRepository customerRepository,
                        OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        OptionTypeRepository optionTypeRepository,
                        ShippingAddressRepository shippingAddressRepository,
                        ShippingHistoryRepository shippingHistoryRepository,
                        PaymentRepository paymentRepository,
                        ReviewRepository reviewRepository) {
        Faker faker = new Faker();


        // Generate merchants
        List<Merchant> merchants = IntStream.range(0, 5)
                .mapToObj(i -> {
                    Merchant merchant = new Merchant();
                    merchant.setName(faker.company().name());
                    merchant.setAddress(faker.address().streetAddress());
                    return merchant;
                })
                .collect(Collectors.toList());
        merchantRepository.saveAll(merchants);

        // Generate option types (e.g., color, size)
        List<OptionType> optionTypes = Arrays.asList(
                new OptionType("Color"),
                new OptionType("Size")
        );
        optionTypeRepository.saveAll(optionTypes);

        // Generate option values for colors and sizes
        List<OptionValue> optionValues = new ArrayList<>();
        List<String> colors = Arrays.asList("Red", "Blue", "Green", "Yellow");
        List<String> sizes = Arrays.asList("S", "M", "L", "XL");
        for (OptionType optionType : optionTypes) {
            List<String> values = optionType.getName().equals("Color") ? colors : sizes;
            for (String value : values) {
                OptionValue optionValue = new OptionValue();
                optionValue.setValue(value);
                optionValue.setOptionType(optionType);
                optionValues.add(optionValue);
            }
        }
        optionValueRepository.saveAll(optionValues);

        // Generate categories with parent categories
        List<Category> parentCategories = IntStream.range(0, 3)
                .mapToObj(i -> {
                    Category parentCategory = new Category();
                    parentCategory.setName(faker.commerce().department());
                    return parentCategory;
                })
                .collect(Collectors.toList());
        categoryRepository.saveAll(parentCategories);

        List<Category> categories = IntStream.range(0, 5)
                .mapToObj(i -> {
                    Category category = new Category();
                    category.setName(faker.commerce().department());
                    Collections.shuffle(parentCategories);
                    category.setParent(parentCategories.get(0)); // Set parent category
                    return category;
                })
                .collect(Collectors.toList());
        categoryRepository.saveAll(categories);

        // Generate products with categories, option values, and merchants
        List<Product> products = IntStream.range(0, 20)
                .mapToObj(i -> {
                    Product product = new Product();
                    product.setName(faker.commerce().productName());
                    product.setPrice(Double.parseDouble(faker.commerce().price()));
                    product.setStock(faker.number().numberBetween(1, 100));
                    product.setSku(faker.commerce().promotionCode());
                    Collections.shuffle(categories);
                    product.setCategories(new HashSet<>(categories.subList(0, Math.min(categories.size(), 2))));
                    Collections.shuffle(optionValues);
                    product.setOptionValues(new HashSet<>(optionValues.subList(0, Math.min(optionValues.size(), 2))));
                    Collections.shuffle(merchants);
                    product.setMerchant(merchants.get(0)); // Set merchant
                    return product;
                })
                .collect(Collectors.toList());
        productRepository.saveAll(products);

        // Generate customers
        List<Customer> customers = IntStream.range(0, 10)
                .mapToObj(i -> {
                    Customer customer = new Customer();
                    customer.setName(faker.name().fullName());
                    return customer;
                })
                .collect(Collectors.toList());
        customerRepository.saveAll(customers);

        // Generate shipping addresses for customers
        List<ShippingAddress> shippingAddresses = customers.stream()
                .map(customer -> createShippingAddress(faker, customer))
                .collect(Collectors.toList());
        shippingAddressRepository.saveAll(shippingAddresses);

        // Generate orders
        List<Order> allOrders = new ArrayList<>();
        for (Customer customer : customers) {
            Set<Order> orders = IntStream.range(0, faker.number().numberBetween(1, 4)) // Generate 1 to 3 orders per customer
                    .mapToObj(j -> {
                        Order order = new Order();
                        order.setCustomer(customer);
                        // Create a new shipping address for the order
                        ShippingAddress shippingAddress = createShippingAddress(faker, customer);
                        shippingAddressRepository.save(shippingAddress);
                        order.setShippingAddress(shippingAddress);
                        // Assign a random status
                        order.setStatus(faker.options().option("Pending", "Processing", "Shipped", "Delivered"));
                        return order;
                    })
                    .collect(Collectors.toSet());
            allOrders.addAll(orders);
        }
        orderRepository.saveAll(allOrders);


        // Generate order items with selected option values, ensuring distinct option types
        for (Order order : allOrders) {
            Set<OrderItem> orderItems = IntStream.range(0, faker.number().numberBetween(1, 5)) // Generate 1 to 4 order items per order
                    .mapToObj(k -> {
                        OrderItem orderItem = new OrderItem();
                        Collections.shuffle(products);
                        Product selectedProduct = products.get(0);
                        orderItem.setProduct(selectedProduct);
                        orderItem.setQuantity(faker.number().numberBetween(1, 5));
                        orderItem.setOrder(order);

                        // Group the product's option values by their option type
                        Map<OptionType, List<OptionValue>> optionValuesByType = selectedProduct.getOptionValues().stream()
                                .collect(Collectors.groupingBy(OptionValue::getOptionType));

                        // Randomly select one option value from each option type group
                        Set<OptionValue> selectedOptionValues = optionValuesByType.values().stream()
                                .map(x -> {
                                    Collections.shuffle(x);
                                    return x.get(0);
                                })
                                .collect(Collectors.toSet());

                        orderItem.setSelectedOptionValues(selectedOptionValues);

                        return orderItem;
                    })
                    .collect(Collectors.toSet());
            orderItemRepository.saveAll(orderItems);
        }

        // Generate payments for orders
        List<Payment> payments = allOrders.stream()
                .map(order -> {
                    Payment payment = new Payment();
                    payment.setAmount(faker.number().randomDouble(2, 10, 1000));
                    payment.setTransactionId(faker.finance().creditCard());
                    payment.setPaymentDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
                    payment.setPaymentMethod(faker.options().option("Credit Card", "PayPal", "Bank Transfer"));
                    payment.setOrder(order);
                    return payment;
                })
                .collect(Collectors.toList());
        paymentRepository.saveAll(payments);

        // Generate shipping history for order items
        for (OrderItem orderItem : orderItemRepository.findAll()) {
            ShippingHistory shippingHistory = new ShippingHistory();
            shippingHistory.setStatus(faker.options().option("Shipped", "In Transit", "Delivered"));
            shippingHistory.setUpdateDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 15)));
            shippingHistory.setCarrier(faker.options().option("UPS", "FedEx", "DHL"));
            shippingHistory.setOrderItem(orderItem);
            shippingHistoryRepository.save(shippingHistory);
        }

        // Generate reviews for products
        List<Review> reviews = products.stream()
                .flatMap(product -> IntStream.range(0, faker.number().numberBetween(1, 5))
                        .mapToObj(i -> {
                            Review review = new Review();
                            review.setContent(faker.lorem().sentence());
                            review.setRating(faker.number().numberBetween(1, 5));
                            review.setCustomer(faker.options().nextElement(customers));
                            review.setProduct(product);
                            return review;
                        }))
                .collect(Collectors.toList());
        reviewRepository.saveAll(reviews);
    }

    private ShippingAddress createShippingAddress(Faker faker, Customer customer) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setStreet(faker.address().streetAddress());
        shippingAddress.setCity(faker.address().city());
        shippingAddress.setState(faker.address().state());
        shippingAddress.setCountry(faker.address().country());
        shippingAddress.setPostalCode(faker.address().zipCode());
        shippingAddress.setCustomer(customer);
        return shippingAddress;
    }
}
