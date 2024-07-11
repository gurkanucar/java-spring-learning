package com.gucardev.springlearning.parent_child_caching.mapper;

import com.gucardev.springlearning.enum_service.SpringContext;
import com.gucardev.springlearning.parent_child_caching.dto.CustomerDto;
import com.gucardev.springlearning.parent_child_caching.dto.UserDto;
import com.gucardev.springlearning.parent_child_caching.dao.entity.Customerr;
import com.gucardev.springlearning.parent_child_caching.service.UserServicee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerMapperr {


    @Mapping(source = "assigneeUser", target = "assigneeUserDto", qualifiedByName = "userIdToUserDto")
    CustomerDto customerToCustomerDto(Customerr customer);

    @Named("userIdToUserDto")
    default UserDto userIdToUserDto(Long id) {
        if (id == null) {
            return null;
        }
        return SpringContext.getApplicationContext(UserServicee.class).getUserDtoById(id);
    }
}
