package org.gucardev;

import lombok.Builder;
import lombok.Data;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;

import javax.lang.model.element.Modifier;

@Data
@Builder
public class FieldDefinition {
    private Class<?> fieldType;
    private String fieldName;
    private String columnName;
    private Integer columnLength;

    // This method returns the appropriate JavaPoet FieldSpec object based on the field definition
    public FieldSpec toFieldSpec() {
        FieldSpec.Builder fieldBuilder = FieldSpec.builder(fieldType, fieldName, Modifier.PRIVATE);

        if (columnName != null || columnLength != null) {
            AnnotationSpec.Builder columnAnnotationBuilder = AnnotationSpec.builder(ClassName.get("jakarta.persistence", "Column"));

            if (columnName != null) {
                columnAnnotationBuilder.addMember("name", "$S", columnName);
            }
            if (columnLength != null) {
                columnAnnotationBuilder.addMember("length", "$L", columnLength);
            }

            fieldBuilder.addAnnotation(columnAnnotationBuilder.build());
        }

        return fieldBuilder.build();
    }
}
