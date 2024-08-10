package org.gucardev.springboottest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;

  private String username;
  private String email;
  private String name;
}
