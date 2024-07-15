package org.gucardev.entityauditing.dto;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.entityauditing.enumeration.RevType;

import java.util.Date;

@Getter
@Setter
public class UserHistoryDTO {
  private Long id;
  private String name;
  private String username;

  // Revision Details
  private Long rev;
  private RevType revType;
  // private int revType;
  private Date revDate;
}
