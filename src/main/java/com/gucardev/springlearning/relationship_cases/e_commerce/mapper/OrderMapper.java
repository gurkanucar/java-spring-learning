package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Customer;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Order;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"
        , uses = {ProductMapper.class} // if we comment it we may use toProductDto below -- if we have
        //     some custom mappings otherwise even if we dont use it will map same fields automatically
)
public interface OrderMapper {
    OrderDTO toDto(Order order);

    OrderDTO.CustomerDTO toCustomerDto(Customer customer);

    OrderDTO.OrderItemDTO toOrderItemDto(OrderItem orderItem);

    // if you dont use `uses` we can convert via this
//    ProductDTO toProductDto(Product product);
}

