package org.gucardev.springboottest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

  private Long id;

  private String title;

  private String detail;

  private Long userId;
}
