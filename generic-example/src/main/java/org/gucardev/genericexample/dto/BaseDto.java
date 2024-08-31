package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String updatedBy;

}
