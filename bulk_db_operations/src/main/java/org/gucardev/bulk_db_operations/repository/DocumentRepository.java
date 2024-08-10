package org.gucardev.bulk_db_operations.repository;

import org.gucardev.bulk_db_operations.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
