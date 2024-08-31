package org.gucardev;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;

import javax.lang.model.element.Modifier;
import java.util.UUID;

public class IdGenerator {

    public static FieldSpec generateIdField(boolean useUUID) {
        ClassName idAnnotation = ClassName.get("jakarta.persistence", "Id");
        ClassName generatedValueAnnotation = ClassName.get("jakarta.persistence", "GeneratedValue");
        ClassName generationType = ClassName.get("jakarta.persistence", "GenerationType");

        if (useUUID) {
            return FieldSpec.builder(UUID.class, "id", Modifier.PRIVATE)
                    .addAnnotation(idAnnotation)
                    .addAnnotation(AnnotationSpec.builder(ClassName.get("org.hibernate.annotations", "GenericGenerator"))
                            .addMember("name", "$S", "UUID")
                            .addMember("strategy", "$S", "org.hibernate.id.UUIDGenerator")
                            .build())
                    .addAnnotation(AnnotationSpec.builder(generatedValueAnnotation)
                            .addMember("generator", "$S", "UUID")
                            .build())
                    .build();
        } else {
            return FieldSpec.builder(Long.class, "id", Modifier.PRIVATE)
                    .addAnnotation(idAnnotation)
                    .addAnnotation(AnnotationSpec.builder(generatedValueAnnotation)
                            .addMember("strategy", "$T.IDENTITY", generationType)
                            .build())
                    .build();
        }
    }
}
