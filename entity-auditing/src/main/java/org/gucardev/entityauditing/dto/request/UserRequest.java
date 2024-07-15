package org.gucardev.entityauditing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  private Long id;
  private String name;
  private String username;
}
