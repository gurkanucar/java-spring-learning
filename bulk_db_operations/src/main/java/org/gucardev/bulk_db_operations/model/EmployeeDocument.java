package org.gucardev.bulk_db_operations.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmployeeDocument extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    private LocalDateTime assignedDate;

    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus = ReadStatus.NOT_READ;

    public enum ReadStatus {
        NOT_READ,
        READ
    }
}
