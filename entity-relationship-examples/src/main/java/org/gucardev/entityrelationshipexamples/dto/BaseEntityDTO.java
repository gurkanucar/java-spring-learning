package org.gucardev.entityrelationshipexamples.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntityDTO {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String updatedBy;
}


