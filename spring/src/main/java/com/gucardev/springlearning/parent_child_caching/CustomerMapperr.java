package com.gucardev.springlearning.parent_child_caching;

import com.gucardev.springlearning.enum_service.SpringContext;
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
