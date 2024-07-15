package org.gucardev.dtofieldmasker.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotationsInside
@JsonSerialize(using = MaskDataSerializer.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskData {
    int value() default 3;

    String replaceChar() default "x";

    MaskingOption maskingOption() default MaskingOption.LAST_X_CHARS_PLAIN;

    enum MaskingOption {
        FIRST_X_CHARS_PLAIN,
        FIRST_X_CHARS_MASKED,
        LAST_X_CHARS_PLAIN,
        LAST_X_CHARS_MASKED
    }
}
