package org.gucardev;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Select UUID or Long for ID generation
        boolean useUUID = false; // Set to false to use Long

        // Define the class name
        ClassName className = ClassName.get("com.example.generated", "Person");

        // Define Lombok @Getter and @Setter annotations
        ClassName getterAnnotation = ClassName.get("lombok", "Getter");
        ClassName setterAnnotation = ClassName.get("lombok", "Setter");

        // Define JPA @Entity annotation
        ClassName entityAnnotation = ClassName.get("jakarta.persistence", "Entity");

        // Generate ID field
        FieldSpec idField = IdGenerator.generateIdField(useUUID);

        // Define additional fields
        List<FieldDefinition> fieldDefinitions = new ArrayList<>();
        fieldDefinitions.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("name")
                .columnName("person_name")
                .columnLength(100)
                .build());

        fieldDefinitions.add(FieldDefinition.builder()
                .fieldType(String.class)
                .fieldName("email")
                .columnLength(200)
                .build());

        // Build the class
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(getterAnnotation)
                .addAnnotation(setterAnnotation)
                .addAnnotation(entityAnnotation)
                .addField(idField);

        // Add each field to the class
        for (FieldDefinition fieldDefinition : fieldDefinitions) {
            classBuilder.addField(fieldDefinition.toFieldSpec());
        }

        TypeSpec personClass = classBuilder.build();

        // Generate the .java file
        JavaFile javaFile = JavaFile.builder("com.example.generated", personClass)
                .build();

        // Write to the specified directory or print to console
        javaFile.writeTo(System.out);
    }
}
