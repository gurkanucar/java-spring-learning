package org.gucardev.bulk_db_operations.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Document extends BaseEntity {

    private String title;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<EmployeeDocument> employeeDocuments;
}
