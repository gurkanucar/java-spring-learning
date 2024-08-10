package org.gucardev.bulk_db_operations.repository;

import org.gucardev.bulk_db_operations.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

