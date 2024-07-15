package org.gucardev.entityauditing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
  private String street;
  private String city;
  private String country;
  private Long userId;
  private Long id;
}
