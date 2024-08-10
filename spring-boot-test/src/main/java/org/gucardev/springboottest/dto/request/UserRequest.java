package org.gucardev.springboottest.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  private Long id;

  @NotBlank
  @Length(max = 30, min = 3)
  private String username;

  @Email @NotBlank private String email;
  @NotBlank private String name;
}
