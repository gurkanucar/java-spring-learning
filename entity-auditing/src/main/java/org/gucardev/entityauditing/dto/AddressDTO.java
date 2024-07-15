package org.gucardev.entityauditing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
  private Long id;
  private String street;
  private String city;
  private String country;
  private Long userId;
  private String username;
}
