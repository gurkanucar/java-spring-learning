package com.gucardev.springlearning.relationship_cases.e_commerce;

import com.github.javafaker.Faker;
import com.gucardev.springlearning.relationship_cases.e_commerce.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

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
                                               OptionTypeRepository optionTypeRepository) {
        return args -> {
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

            // Generate option types
            List<OptionType> optionTypes = IntStream.range(0, 5)
                    .mapToObj(i -> {
                        OptionType optionType = new OptionType();
                        optionType.setName(faker.commerce().productName()); // Use a suitable name for the option type
                        return optionType;
                    })
                    .collect(Collectors.toList());
            optionTypeRepository.saveAll(optionTypes);

            // Generate option values with option types
            List<OptionValue> optionValues = IntStream.range(0, 10)
                    .mapToObj(i -> {
                        OptionValue optionValue = new OptionValue();
                        List<String> colors = Arrays.asList("Red", "Blue", "Green", "Yellow");
                        Collections.shuffle(colors);
                        optionValue.setValue(colors.get(0));
                        Collections.shuffle(optionTypes);
                        optionValue.setOptionType(optionTypes.get(0)); // Set option type
                        return optionValue;
                    })
                    .collect(Collectors.toList());
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

            // Generate orders
            List<Order> allOrders = new ArrayList<>();
            for (Customer customer : customers) {
                Set<Order> orders = IntStream.range(0, 2)
                        .mapToObj(j -> {
                            Order order = new Order();
                            order.setCustomer(customer);
                            return order;
                        })
                        .collect(Collectors.toSet());
                allOrders.addAll(orders);
            }
            orderRepository.saveAll(allOrders);

            // Generate order items
            for (Order order : allOrders) {
                Set<OrderItem> orderItems = IntStream.range(0, 3)
                        .mapToObj(k -> {
                            OrderItem orderItem = new OrderItem();
                            Collections.shuffle(products);
                            orderItem.setProduct(products.get(0));
                            orderItem.setQuantity(faker.number().numberBetween(1, 5));
                            orderItem.setOrder(order);
                            return orderItem;
                        })
                        .collect(Collectors.toSet());
                orderItemRepository.saveAll(orderItems);
            }
        };
    }
}
