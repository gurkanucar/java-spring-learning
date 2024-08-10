package org.gucardev.bulk_db_operations.service;

import org.gucardev.bulk_db_operations.model.Document;
import org.gucardev.bulk_db_operations.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional
    public Document createDocument(String title) {
        Document document = new Document();
        document.setTitle(title);
        return documentRepository.save(document);
    }

    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Transactional
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
        System.out.println("Document with id " + documentId + " and its associated employee documents have been deleted.");
    }

}
