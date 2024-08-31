package org.gucardev;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExampleGenerator {

    public static void main(String[] args) throws IOException {
        // Select UUID or Long for ID generation
        boolean useUUID = false; // Set to false to use Long

        // Define Lombok @Getter and @Setter annotations
        ClassName getterAnnotation = ClassName.get("lombok", "Getter");
        ClassName setterAnnotation = ClassName.get("lombok", "Setter");

        // Define JPA @Entity annotation
        ClassName entityAnnotation = ClassName.get("jakarta.persistence", "Entity");

        // Generate Person class
        ClassName personClassName = ClassName.get("com.example.generated.entity", "Person");
        FieldSpec personIdField = IdGenerator.generateIdField(useUUID);
        List<FieldDefinition> personFields = new ArrayList<>();
        personFields.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("name")
                .columnName("person_name")
                .columnLength(100)
                .build());

        personFields.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("email")
                .columnLength(200)
                .build());

        TypeSpec.Builder personClassBuilder = TypeSpec.classBuilder(personClassName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(getterAnnotation)
                .addAnnotation(setterAnnotation)
                .addAnnotation(entityAnnotation)
                .addField(personIdField);

        for (FieldDefinition field : personFields) {
            personClassBuilder.addField(field.toFieldSpec());
        }

        // Generate Address class
        ClassName addressClassName = ClassName.get("com.example.generated.entity", "Address");
        FieldSpec addressIdField = IdGenerator.generateIdField(useUUID);
        List<FieldDefinition> addressFields = new ArrayList<>();
        addressFields.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("street")
                .columnLength(200)
                .build());

        addressFields.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("city")
                .columnLength(100)
                .build());

        TypeSpec.Builder addressClassBuilder = TypeSpec.classBuilder(addressClassName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(getterAnnotation)
                .addAnnotation(setterAnnotation)
                .addAnnotation(entityAnnotation)
                .addField(addressIdField);

        for (FieldDefinition field : addressFields) {
            addressClassBuilder.addField(field.toFieldSpec());
        }

        // Add OneToMany relationship in Person class
        RelationshipDefinition addressesField = RelationshipDefinition.builder()
                .fieldType(ClassName.get(List.class))  // Specify the List type
                .genericType(addressClassName)         // Specify the generic type (e.g., Address)
                .fieldName("addresses")
                .mappedBy("person")
                .relationshipType(RelationshipDefinition.RelationshipType.ONETOMANY)
                .build();

        // Add the field to the person class
        personClassBuilder.addField(addressesField.toFieldSpec());

        // Add ManyToOne relationship in Address class
        RelationshipDefinition personField = RelationshipDefinition.builder()
                .fieldType(personClassName)
                .fieldName("person")
                .joinColumnName("person_id")
                .relationshipType(RelationshipDefinition.RelationshipType.MANYTOONE)
                .build();
        addressClassBuilder.addField(personField.toFieldSpec());

        TypeSpec personClass = personClassBuilder.build();
        TypeSpec addressClass = addressClassBuilder.build();

        // Specify the package and directory for the generated classes
        JavaFile personJavaFile = JavaFile.builder("com.example.generated.entity", personClass)
                .build();
        JavaFile addressJavaFile = JavaFile.builder("com.example.generated.entity", addressClass)
                .build();

        // Save the files to the specified directory
        personJavaFile.writeTo(Paths.get("./src/main/java"));
        addressJavaFile.writeTo(Paths.get("./src/main/java"));
    }
}
