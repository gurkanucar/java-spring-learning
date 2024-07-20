package org.gucardev.entityrelationshipexamples;

import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.gucardev.entityrelationshipexamples.repository.LookupCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LookupCategoryRepository lookupCategoryRepository;

    @Override
    @Transactional
    public void run(String... args) {
        createInitialCategoriesAndValues();
    }

    private void createInitialCategoriesAndValues() {
        LookupCategory occupationCategory = new LookupCategory();
        occupationCategory.setName("Occupation");

        LookupValue developer = new LookupValue();
        developer.setLookupValue("Developer");
        developer.setCategory(occupationCategory);

        LookupValue tester = new LookupValue();
        tester.setLookupValue("Tester");
        tester.setCategory(occupationCategory);

        occupationCategory.setLookupValues(Arrays.asList(developer, tester));
        lookupCategoryRepository.save(occupationCategory);

        LookupCategory statusCategory = new LookupCategory();
        statusCategory.setName("Status");

        LookupValue active = new LookupValue();
        active.setLookupValue("Active");
        active.setCategory(statusCategory);

        LookupValue inactive = new LookupValue();
        inactive.setLookupValue("Inactive");
        inactive.setCategory(statusCategory);

        statusCategory.setLookupValues(Arrays.asList(active, inactive));
        lookupCategoryRepository.save(statusCategory);

    }
}
