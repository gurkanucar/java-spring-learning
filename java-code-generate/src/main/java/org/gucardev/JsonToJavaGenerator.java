package org.gucardev;

import com.google.gson.Gson;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonToJavaGenerator {

    static class ClassDefinition {
        String name;
        List<Field> fields;
        List<Relationship> relationships;
    }

    static class Field {
        String type;
        String name;
        String columnName;
        Integer columnLength;
    }

    static class Relationship {
        String type;
        String targetClass;
        String fieldName;
        String mappedBy;
        String joinColumnName;
    }

    static class Package {
        String packageName;
        List<ClassDefinition> classes;
    }

    public static void main(String[] args) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\user\\IdeaProjects\\spring-learning\\java-code-generate\\src\\main\\resources\\a.json")));
        Gson gson = new Gson();
        Package pkg = gson.fromJson(jsonContent, Package.class);

        for (ClassDefinition classDef : pkg.classes) {
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(classDef.name)
                    .addModifiers(Modifier.PUBLIC);

            for (Field field : classDef.fields) {
                FieldSpec.Builder fieldBuilder = FieldSpec.builder(String.class, field.name, Modifier.PRIVATE);
                if (field.columnLength != null) {
                    // You can add annotations here based on your requirement
                }
                classBuilder.addField(fieldBuilder.build());
            }

            for (Relationship relationship : classDef.relationships) {
                TypeName fieldType = ClassName.bestGuess(relationship.targetClass);
                if ("OneToMany".equals(relationship.type) || "ManyToOne".equals(relationship.type)) {
                    // You can modify this to add specific annotations or handle collections
                    classBuilder.addField(FieldSpec.builder(fieldType, relationship.fieldName, Modifier.PRIVATE).build());
                }
            }

            JavaFile javaFile = JavaFile.builder(pkg.packageName, classBuilder.build()).build();
            javaFile.writeTo(Paths.get("./src/main/java"));
        }
    }
}
