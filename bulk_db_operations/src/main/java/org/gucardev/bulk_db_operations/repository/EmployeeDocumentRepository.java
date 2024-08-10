package org.gucardev.bulk_db_operations.repository;

import org.gucardev.bulk_db_operations.model.Document;
import org.gucardev.bulk_db_operations.model.Employee;
import org.gucardev.bulk_db_operations.model.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    Optional<EmployeeDocument> findByEmployeeAndDocument(Employee employee, Document document);
}
