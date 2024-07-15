package org.gucardev.utilities;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.utilities.utils.TestDataUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDataUtilTest {

    @Test
    void testInitializeComplexClass() {
        ComplexClass complexObject = TestDataUtil.initialize(ComplexClass.class);

        assertNotNull(complexObject);
        assertTrue(complexObject.isBooleanField());
        assertTrue(complexObject.getBooleanWrapperField());
        assertEquals((byte) 1, complexObject.getByteField());
        assertEquals(Byte.valueOf((byte) 1), complexObject.getByteWrapperField());
        assertEquals('A', complexObject.getCharField());
        assertEquals(Character.valueOf('A'), complexObject.getCharWrapperField());
        assertEquals((short) 1, complexObject.getShortField());
        assertEquals(Short.valueOf((short) 1), complexObject.getShortWrapperField());
        assertEquals(1, complexObject.getIntField());
        assertEquals(Integer.valueOf(1), complexObject.getIntWrapperField());
        assertEquals(1L, complexObject.getLongField());
        assertEquals(Long.valueOf(1L), complexObject.getLongWrapperField());
        assertEquals(1.0f, complexObject.getFloatField());
        assertEquals(Float.valueOf(1.0f), complexObject.getFloatWrapperField());
        assertEquals(1.0, complexObject.getDoubleField());
        assertEquals(Double.valueOf(1.0), complexObject.getDoubleWrapperField());
        assertEquals("default", complexObject.getStringField());
        assertEquals(BigDecimal.ONE, complexObject.getBigDecimalField());
        assertNotNull(complexObject.getListField());
        assertTrue(complexObject.getListField().isEmpty());
        assertNotNull(complexObject.getNestedObjectField());
        assertEquals("default", complexObject.getNestedObjectField().getNestedStringField());
        assertNotNull(complexObject.getEnumField());
        assertEquals(ComplexClass.SampleEnum.OPTION1, complexObject.getEnumField());
    }

    @Getter
    @Setter
    public static class ComplexClass {
        public boolean booleanField;
        public Boolean booleanWrapperField;
        public byte byteField;
        public Byte byteWrapperField;
        public char charField;
        public Character charWrapperField;
        public short shortField;
        public Short shortWrapperField;
        public int intField;
        public Integer intWrapperField;
        public long longField;
        public Long longWrapperField;
        public float floatField;
        public Float floatWrapperField;
        public double doubleField;
        public Double doubleWrapperField;
        public String stringField;
        public BigDecimal bigDecimalField;
        public List<String> listField;
        public NestedObject nestedObjectField;
        public SampleEnum enumField;

        @Getter
        @Setter
        public static class NestedObject {
            public String nestedStringField;
        }

        @Getter
        public enum SampleEnum {
            OPTION1, OPTION2, OPTION3
        }

    }


}