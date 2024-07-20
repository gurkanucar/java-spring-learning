package org.gucardev.entityrelationshipexamples;

import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.gucardev.entityrelationshipexamples.model.User;
import org.gucardev.entityrelationshipexamples.repository.LookupCategoryRepository;
import org.gucardev.entityrelationshipexamples.repository.LookupValueRepository;
import org.gucardev.entityrelationshipexamples.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LookupCategoryRepository categoryRepository;

    @Autowired
    private LookupValueRepository valueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {
        createInitialCategoriesAndValues();
        createUsers();
    }

    private void createInitialCategoriesAndValues() {
        // Create Categories
        LookupCategory occupationCategory = createCategory("occupation", "Occupation", "Meslek", "Ocupación");
        LookupCategory statusCategory = createCategory("status", "Status", "Durum", "Estado");

        // Create Values for "Occupation"
        createLookupValue(occupationCategory, "Engineer", "Mühendis", "Ingeniero");
        createLookupValue(occupationCategory, "Doctor", "Doktor", "Doctor");
        createLookupValue(occupationCategory, "Teacher", "Öğretmen", "Profesor");
        createLookupValue(occupationCategory, "Artist", "Sanatçı", "Artista");

        // Create Values for "Status"
        createLookupValue(statusCategory, "Active", "Aktif", "Activo");
        createLookupValue(statusCategory, "Inactive", "Pasif", "Inactivo");
    }

    private void createUsers() {
        LookupValue engineer = valueRepository.findByKey("Engineer");
        LookupValue doctor = valueRepository.findByKey("Doctor");
        LookupValue teacher = valueRepository.findByKey("Teacher");
        LookupValue artist = valueRepository.findByKey("Artist");
        LookupValue active = valueRepository.findByKey("Active");

        createUser("Ali Doe", "johndoe@example.com", engineer, active);
        createUser("Metin Smith", "janesmith@example.com", doctor, active);
        createUser("Sezai Smith", "janesmith@example.com", teacher, active);
        createUser("Kartal Smith", "janesmith@example.com", artist, active);
        createUser("Halil Smith", "janesmith@example.com", artist, active);
        createUser("Şerif Smith", "janesmith@example.com", engineer, active);
    }

    private LookupCategory createCategory(String key, String displayValue, String displayValueTr, String displayValueEs) {
        LookupCategory category = new LookupCategory();
        category.setKey(key);
        category.setDisplayValue(displayValue);
        category.setTranslations(Map.of("tr", displayValueTr, "es", displayValueEs));
        return categoryRepository.save(category);
    }

    private void createLookupValue(LookupCategory category, String key, String valueTr, String valueEs) {
        LookupValue lookupValue = new LookupValue();
        lookupValue.setKey(key);
        lookupValue.setCategory(category);
        lookupValue.setDisplayValue(key);
        lookupValue.setTranslations(Map.of("tr", valueTr, "es", valueEs));
        valueRepository.save(lookupValue);
    }

    private void createUser(String name, String email, LookupValue occupation, LookupValue status) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setOccupation(occupation);
        user.setStatus(status);
        userRepository.save(user);
    }
}
