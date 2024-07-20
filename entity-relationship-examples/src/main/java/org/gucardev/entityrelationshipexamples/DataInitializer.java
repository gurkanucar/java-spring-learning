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
        LookupCategory occupationCategory = getLookupCategory("Occupation", "Developer", "Tester");
        lookupCategoryRepository.save(occupationCategory);

        LookupCategory statusCategory = getLookupCategory("Status", "Active", "Inactive");
        lookupCategoryRepository.save(statusCategory);

    }

    private static LookupCategory getLookupCategory(String Occupation, String Developer, String Tester) {
        LookupCategory occupationCategory = new LookupCategory();
        occupationCategory.setName(Occupation);
        occupationCategory.setDisplayValue(Occupation);

        LookupValue developer = new LookupValue();
        developer.setLookupValue(Developer);
        developer.setDisplayValue(Developer);
        developer.setCategory(occupationCategory);

        LookupValue tester = new LookupValue();
        tester.setLookupValue(Tester);
        tester.setDisplayValue(Tester);
        tester.setCategory(occupationCategory);

        occupationCategory.setLookupValues(Arrays.asList(developer, tester));
        return occupationCategory;
    }
}
