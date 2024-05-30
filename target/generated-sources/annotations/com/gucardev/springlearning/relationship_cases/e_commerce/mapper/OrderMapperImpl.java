package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderDTO.CustomerDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderDTO.ShippingAddressDto;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderItemDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderItemDTO.OptionTypeDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderItemDTO.OptionValueDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.CategoryDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.MerchantDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Category;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Customer;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Merchant;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionType;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionValue;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Order;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OrderItem;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Product;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.ShippingAddress;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setCustomer( toCustomerDto( order.getCustomer() ) );
        orderDTO.setOrderItems( orderItemSetToOrderItemDTOSet( order.getOrderItems() ) );
        orderDTO.setShippingAddress( shippingAddressToShippingAddressDto( order.getShippingAddress() ) );
        orderDTO.setStatus( order.getStatus() );

        return orderDTO;
    }

    @Override
    public CustomerDTO toCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );

        return customerDTO;
    }

    @Override
    public OrderItemDTO toOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId( orderItem.getId() );
        orderItemDTO.setQuantity( orderItem.getQuantity() );
        orderItemDTO.setProduct( toProductDto( orderItem.getProduct() ) );
        orderItemDTO.setSelectedOptionValues( optionValueSetToOptionValueDTOSet( orderItem.getSelectedOptionValues() ) );

        return orderItemDTO;
    }

    @Override
    public ProductDTO toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setStock( product.getStock() );
        productDTO.setSku( product.getSku() );
        productDTO.setMerchant( merchantToMerchantDTO( product.getMerchant() ) );
        productDTO.setCategories( categorySetToCategoryDTOSet( product.getCategories() ) );

        return productDTO;
    }

    protected Set<OrderItemDTO> orderItemSetToOrderItemDTOSet(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderItemDTO> set1 = new HashSet<OrderItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderItem orderItem : set ) {
            set1.add( toOrderItemDto( orderItem ) );
        }

        return set1;
    }

    protected ShippingAddressDto shippingAddressToShippingAddressDto(ShippingAddress shippingAddress) {
        if ( shippingAddress == null ) {
            return null;
        }

        ShippingAddressDto shippingAddressDto = new ShippingAddressDto();

        shippingAddressDto.setId( shippingAddress.getId() );
        shippingAddressDto.setStreet( shippingAddress.getStreet() );
        shippingAddressDto.setCity( shippingAddress.getCity() );
        shippingAddressDto.setState( shippingAddress.getState() );
        shippingAddressDto.setCountry( shippingAddress.getCountry() );
        shippingAddressDto.setPostalCode( shippingAddress.getPostalCode() );

        return shippingAddressDto;
    }

    protected OptionTypeDTO optionTypeToOptionTypeDTO(OptionType optionType) {
        if ( optionType == null ) {
            return null;
        }

        OptionTypeDTO optionTypeDTO = new OptionTypeDTO();

        optionTypeDTO.setId( optionType.getId() );
        optionTypeDTO.setName( optionType.getName() );

        return optionTypeDTO;
    }

    protected OptionValueDTO optionValueToOptionValueDTO(OptionValue optionValue) {
        if ( optionValue == null ) {
            return null;
        }

        OptionValueDTO optionValueDTO = new OptionValueDTO();

        optionValueDTO.setId( optionValue.getId() );
        optionValueDTO.setValue( optionValue.getValue() );
        optionValueDTO.setOptionType( optionTypeToOptionTypeDTO( optionValue.getOptionType() ) );

        return optionValueDTO;
    }

    protected Set<OptionValueDTO> optionValueSetToOptionValueDTOSet(Set<OptionValue> set) {
        if ( set == null ) {
            return null;
        }

        Set<OptionValueDTO> set1 = new HashSet<OptionValueDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OptionValue optionValue : set ) {
            set1.add( optionValueToOptionValueDTO( optionValue ) );
        }

        return set1;
    }

    protected MerchantDTO merchantToMerchantDTO(Merchant merchant) {
        if ( merchant == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setId( merchant.getId() );
        merchantDTO.setName( merchant.getName() );
        merchantDTO.setAddress( merchant.getAddress() );

        return merchantDTO;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new HashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDTO( category ) );
        }

        return set1;
    }
}
