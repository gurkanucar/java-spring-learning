package org.gucardev;

import com.squareup.javapoet.*;
import lombok.Builder;
import lombok.Data;

import javax.lang.model.element.Modifier;
import java.util.List;

@Data
@Builder
public class RelationshipDefinition {
    private TypeName fieldType;
    private String fieldName;
    private String mappedBy;
    private String joinColumnName;
    private RelationshipType relationshipType;
    private TypeName genericType;  // Type of the list's elements

    public FieldSpec toFieldSpec() {
        TypeName actualFieldType = fieldType;

        // If the relationship is a List, parameterize it with the generic type
        if (fieldType.equals(ClassName.get(List.class)) && genericType != null) {
            actualFieldType = ParameterizedTypeName.get(ClassName.get(List.class), genericType);
        }

        FieldSpec.Builder fieldBuilder = FieldSpec.builder(actualFieldType, fieldName, Modifier.PRIVATE);
        AnnotationSpec.Builder relationshipAnnotationBuilder = null;

        switch (relationshipType) {
            case ONETOONE:
                relationshipAnnotationBuilder = AnnotationSpec.builder(ClassName.get("jakarta.persistence", "OneToOne"));
                break;
            case MANYTOONE:
                relationshipAnnotationBuilder = AnnotationSpec.builder(ClassName.get("jakarta.persistence", "ManyToOne"))
                        .addMember("fetch", "$T.LAZY", ClassName.get("jakarta.persistence.FetchType", "FetchType"));
                fieldBuilder.addAnnotation(AnnotationSpec.builder(ClassName.get("jakarta.persistence", "JoinColumn"))
                        .addMember("name", "$S", joinColumnName)
                        .build());
                break;
            case ONETOMANY:
                relationshipAnnotationBuilder = AnnotationSpec.builder(ClassName.get("jakarta.persistence", "OneToMany"))
                        .addMember("mappedBy", "$S", mappedBy)
                        .addMember("cascade", "$T.ALL", ClassName.get("jakarta.persistence.CascadeType", "CascadeType"))
                        .addMember("orphanRemoval", "$L", true)
                        .addMember("fetch", "$T.LAZY", ClassName.get("jakarta.persistence.FetchType", "FetchType"));
                break;
            case MANYTOMANY:
                relationshipAnnotationBuilder = AnnotationSpec.builder(ClassName.get("jakarta.persistence", "ManyToMany"));
                break;
        }

        if (relationshipAnnotationBuilder != null) {
            fieldBuilder.addAnnotation(relationshipAnnotationBuilder.build());
        }

        return fieldBuilder.build();
    }

    public enum RelationshipType {
        ONETOONE, MANYTOONE, ONETOMANY, MANYTOMANY
    }
}