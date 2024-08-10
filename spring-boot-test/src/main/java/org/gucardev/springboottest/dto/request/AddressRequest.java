package org.gucardev.springboottest.dto.request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

  private Long id;

  @NotBlank private String title;

  private String detail;
  @NotNull private Long userId;
}
