package org.gucardev.entityauditing.mapper;


import org.gucardev.entityauditing.dto.AddressHistoryDTO;
import org.gucardev.entityauditing.enumeration.RevType;
import org.gucardev.entityauditing.model.Address;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Date;


@Mapper(componentModel="spring")
public interface AddressHistoryMapper {
  AddressHistoryMapper INSTANCE = Mappers.getMapper(AddressHistoryMapper.class);

  @Mapping(target = "rev", source = "revisionEntity.id")
  @Mapping(target = "revType", source = "revisionType")
  @Mapping(
      target = "revDate",
      source = "revisionEntity.timestamp",
      qualifiedByName = "mapTimestampToDate")
  @Mapping(target = "id", source = "address.id")
  AddressHistoryDTO addressToUserHistoryDTO(
          Address address, RevisionType revisionType, DefaultRevisionEntity revisionEntity);

  default RevType map(RevisionType value) {
    return RevType.fromInt(value.ordinal());
  }

  @Named("mapTimestampToDate")
  default Date mapTimestampToDate(long timestamp) {
    return new Date(timestamp);
  }
}
