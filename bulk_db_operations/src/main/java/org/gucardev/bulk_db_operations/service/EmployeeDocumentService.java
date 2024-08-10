package org.gucardev.bulk_db_operations.service;

import lombok.RequiredArgsConstructor;
import org.gucardev.bulk_db_operations.model.Document;
import org.gucardev.bulk_db_operations.model.Employee;
import org.gucardev.bulk_db_operations.model.EmployeeDocument;
import org.gucardev.bulk_db_operations.repository.EmployeeDocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeDocumentService {

    private final EmployeeDocumentRepository employeeDocumentRepository;
    private final EmployeeService employeeService;


    @Transactional
    public void assignDocumentToEmployees(Document document, Employee employee) {
        EmployeeDocument employeeDocument = new EmployeeDocument();
        employeeDocument.setEmployee(employee);
        employeeDocument.setDocument(document);
        employeeDocument.setAssignedDate(LocalDateTime.now());
        employeeDocumentRepository.save(employeeDocument);
    }

    @Transactional
    public void assignDocumentToEmployees(Document document, List<Employee> employees) {
        List<EmployeeDocument> employeeDocuments = employees.stream()
                .map(employee -> {
                    EmployeeDocument employeeDocument = new EmployeeDocument();
                    employeeDocument.setEmployee(employee);
                    employeeDocument.setDocument(document);
                    employeeDocument.setAssignedDate(LocalDateTime.now());
                    return employeeDocument;
                })
                .collect(Collectors.toList());

        employeeDocumentRepository.saveAll(employeeDocuments);
        System.out.println("Document '" + document.getTitle() + "' has been assigned to " + employees.size() + " employees.");
    }


    @Transactional
    public void updateReadStatus(Employee employee, Document document, EmployeeDocument.ReadStatus readStatus) {
        EmployeeDocument employeeDocument = employeeDocumentRepository
                .findByEmployeeAndDocument(employee, document)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        employeeDocument.setReadStatus(readStatus);
        employeeDocumentRepository.save(employeeDocument);

        System.out.println("Read status for document '" + document.getTitle() + "' has been updated to '" + readStatus + "' for employee '" + employee.getName() + "'.");
    }

    @Transactional
    public void assignDocumentToAllEmployees(Document document, Pageable pageable) {
        Page<Employee> employeePage;
        do {
            employeePage = employeeService.getEmployees(pageable);
            List<Employee> employees = employeePage.getContent();
            assignDocumentToEmployees(document, employees);
            pageable = pageable.next();
        } while (employeePage.hasNext());
    }
}
