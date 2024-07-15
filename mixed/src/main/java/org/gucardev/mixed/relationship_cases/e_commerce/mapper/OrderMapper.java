package org.gucardev.mixed.relationship_cases.e_commerce.mapper;

import org.gucardev.mixed.relationship_cases.e_commerce.dto.OrderDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.dto.OrderItemDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.dto.ProductDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Customer;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Order;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.OrderItem;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring"
        //    , uses = {ProductMapper.class} // if we comment it we may use toProductDto below -- if we have
        //       some custom mappings otherwise even if we dont use it will map same fields automatically
)
public interface OrderMapper {
    OrderDTO toDto(Order order);

    OrderDTO.CustomerDTO toCustomerDto(Customer customer);

    OrderItemDTO toOrderItemDto(OrderItem orderItem);

    // if you dont use `uses` we can convert via this
    @Mapping(target = "optionValues", ignore = true)
    ProductDTO toProductDto(Product product);
}

