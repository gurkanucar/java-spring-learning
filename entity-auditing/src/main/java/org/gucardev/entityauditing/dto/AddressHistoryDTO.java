package org.gucardev.entityauditing.dto;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.entityauditing.enumeration.RevType;

import java.util.Date;

@Getter
@Setter
public class AddressHistoryDTO {
  private Long id;
  private String street;
  private String city;
  private String country;

  // Revision Details
  private Long rev;
  private RevType revType;
  private Date revDate;
}
