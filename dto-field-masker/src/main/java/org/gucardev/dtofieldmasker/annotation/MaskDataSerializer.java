package org.gucardev.dtofieldmasker.annotation;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

public class MaskDataSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (o == null) {
            jsonGenerator.writeNull();
            return;
        }

        MaskData annotation = Objects.requireNonNull(getCurrentField(jsonGenerator, serializerProvider)).getAnnotation(MaskData.class);
        if (annotation == null) {
            jsonGenerator.writeString(o.toString());
            return;
        }

        jsonGenerator.writeString(maskValue(o.toString(), annotation));
    }

    private String maskValue(String text, MaskData annotation) {
        int charCount = Math.min(annotation.value(), text.length());
        String replaceChar = annotation.replaceChar().replaceAll(".*", annotation.replaceChar());  // Ensure replaceChar is a single character
        return switch (annotation.maskingOption()) {
            case LAST_X_CHARS_PLAIN ->
                    replaceWithMask(text.substring(0, text.length() - charCount), replaceChar) + text.substring(text.length() - charCount);
            case LAST_X_CHARS_MASKED ->
                    text.substring(0, text.length() - charCount) + replaceWithMask(text.substring(text.length() - charCount), replaceChar);
            case FIRST_X_CHARS_PLAIN ->
                    text.substring(0, charCount) + replaceWithMask(text.substring(charCount), replaceChar);
            case FIRST_X_CHARS_MASKED ->
                    replaceWithMask(text.substring(0, charCount), replaceChar) + text.substring(charCount);
        };
    }

    private String replaceWithMask(String text, String replaceChar) {
        return text.replaceAll(".", replaceChar);
    }

    private Field getCurrentField(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            return jsonGenerator
                    .getOutputContext()
                    .getCurrentValue()
                    .getClass()
                    .getDeclaredField(jsonGenerator.getOutputContext().getCurrentName());
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
