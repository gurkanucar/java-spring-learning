package org.gucardev.genericexample.config;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class DataGeneratorService {

    @Autowired
    private EntityManager entityManager;

    private Faker faker = new Faker();
    private Map<String, List<Object>> entityMap = new HashMap<>();

    @Transactional
    public void generateData() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Reflections reflections = new Reflections("org.gucardev.genericexample.entity");
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);

        for (Class<?> entityClass : entities) {
            entityMap.putIfAbsent(entityClass.getSimpleName(), new ArrayList<>());
            for (int i = 0; i < 5; i++) {
                Object entityInstance = entityClass.getDeclaredConstructor().newInstance();
                populateFields(entityInstance, entityClass);
                entityManager.persist(entityInstance);
                entityMap.get(entityClass.getSimpleName()).add(entityInstance);
            }
        }
    }

    private void populateFields(Object entityInstance, Class<?> entityClass) throws IllegalAccessException {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                assignRelationship(entityInstance, field);
            } else if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {
                assignCollectionRelationship(entityInstance, field);
            } else {
                fillField(entityInstance, field);
            }
        }
    }

    private void fillField(Object entity, Field field) throws IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        switch (field.getName().toLowerCase()) {
            case "name":
                if (field.getType().equals(String.class)) {
                    field.set(entity, faker.name().fullName());
                }
                break;
            case "email":
                if (field.getType().equals(String.class)) {
                    field.set(entity, faker.internet().emailAddress());
                }
                break;
            case "username":
                if (field.getType().equals(String.class)) {
                    field.set(entity, faker.name().username());
                }
                break;
            case "phone":
            case "phonenumber":
                if (field.getType().equals(String.class)) {
                    field.set(entity, faker.phoneNumber().phoneNumber());
                }
                break;
            case "birthdate":
            case "birthday":
                if (field.getType().equals(Date.class)) {
                    field.set(entity, faker.date().birthday());
                }
                break;
            default:
                assignByType(entity, field);
                break;
        }
    }

    private void assignByType(Object entity, Field field) throws IllegalAccessException {
        if (field.getType().equals(String.class)) {
            field.set(entity, faker.lorem().word());
        } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
            field.set(entity, faker.number().numberBetween(1, 100));
        } else if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
            field.set(entity, faker.number().randomNumber());
        } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
            field.set(entity, faker.number().randomDouble(2, 1, 100));
        } else if (field.getType().equals(Date.class)) {
            field.set(entity, faker.date().past(10000, TimeUnit.DAYS));
        }
    }

    private void assignRelationship(Object entity, Field field) throws IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        List<Object> relatedEntities = entityMap.getOrDefault(field.getType().getSimpleName(), new ArrayList<>());
        if (!relatedEntities.isEmpty()) {
            field.set(entity, relatedEntities.get(faker.random().nextInt(relatedEntities.size())));
        }
    }

    private void assignCollectionRelationship(Object entity, Field field) throws IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        String relatedEntityName = field.getGenericType().getTypeName().substring(
                field.getGenericType().getTypeName().indexOf('<') + 1, field.getGenericType().getTypeName().length() - 1);
        List<Object> relatedEntities = entityMap.getOrDefault(relatedEntityName, new ArrayList<>());
        int numberToAdd = faker.random().nextInt(2, 5); // Randomly decide to add between 1 and 4 related entities

        if (!relatedEntities.isEmpty()) {
            Collection<Object> newCollection = createAppropriateCollection(field.getType());
            Collections.shuffle(relatedEntities);
            relatedEntities.stream().limit(numberToAdd).forEach(newCollection::add);
            field.set(entity, newCollection);
        }
    }

    private Collection<Object> createAppropriateCollection(Class<?> fieldType) {
        // Create appropriate collection based on field type
        if (List.class.isAssignableFrom(fieldType)) {
            return new ArrayList<>();
        } else if (Set.class.isAssignableFrom(fieldType)) {
            return new HashSet<>();
        } else {
            return new ArrayList<>(); // Default to List
        }
    }
}
