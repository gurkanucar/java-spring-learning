package org.gucardev.securityexample4.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseDto {
    private UUID id;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String createdBy;
    private String updatedBy;
}
