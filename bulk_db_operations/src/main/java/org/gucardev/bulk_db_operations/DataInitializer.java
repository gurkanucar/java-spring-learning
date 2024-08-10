package org.gucardev.bulk_db_operations;

import org.gucardev.bulk_db_operations.model.Document;
import org.gucardev.bulk_db_operations.model.Employee;
import org.gucardev.bulk_db_operations.model.EmployeeDocument;
import org.gucardev.bulk_db_operations.service.DocumentService;
import org.gucardev.bulk_db_operations.service.EmployeeDocumentService;
import org.gucardev.bulk_db_operations.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final DocumentService documentService;
    private final EmployeeDocumentService employeeDocumentService;

    public DataInitializer(EmployeeService employeeService, DocumentService documentService, EmployeeDocumentService employeeDocumentService) {
        this.employeeService = employeeService;
        this.documentService = documentService;
        this.employeeDocumentService = employeeDocumentService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create documents
        Document document1 = documentService.createDocument("Employee Handbook");
        Document document2 = documentService.createDocument("Company Policy");
        Document document3 = documentService.createDocument("Project Specification");

        // Create employees
        Employee employee1 = employeeService.createEmployee("John Doe");
        Employee employee2 = employeeService.createEmployee("Jane Smith");
        Employee employee3 = employeeService.createEmployee("Michael Brown");

        // Assign documents to employees
        employeeDocumentService.assignDocumentToEmployees(document1, List.of(employee1, employee2));
        employeeDocumentService.assignDocumentToEmployees(document2, List.of(employee1, employee3));
        employeeDocumentService.assignDocumentToEmployees(document3, List.of(employee2, employee3));

        // Update read status
        employeeDocumentService.updateReadStatus(employee1, document1, EmployeeDocument.ReadStatus.READ);

        // Assign a document to all employees with pagination
        Document document = documentService.createDocument("New Policy");
        Pageable pageable = PageRequest.of(0, 50);
        employeeDocumentService.assignDocumentToAllEmployees(document, pageable);

        // Delete a document and its associated records
        documentService.deleteDocument(document1.getId());

        // Output to confirm data initialization
        System.out.println("Initial data has been populated and read status updated.");
    }
}
