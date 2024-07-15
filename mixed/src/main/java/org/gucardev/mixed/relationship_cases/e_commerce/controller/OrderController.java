package org.gucardev.mixed.relationship_cases.e_commerce.controller;

import org.gucardev.mixed.relationship_cases.e_commerce.dto.OrderDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Order;
import org.gucardev.mixed.relationship_cases.e_commerce.mapper.OrderMapper;
import org.gucardev.mixed.relationship_cases.e_commerce.repo.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderRepository orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }
}
