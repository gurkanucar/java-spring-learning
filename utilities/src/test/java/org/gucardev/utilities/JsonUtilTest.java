package org.gucardev.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void testToJson() {
        Person person = new Person("John", 30);
        String json = JsonUtil.toJson(person);
        assertEquals("{\"name\":\"John\",\"age\":30}", json);
    }

    @Test
    void testFromJson() {
        String json = "{\"name\":\"John\",\"age\":30}";
        Person person = JsonUtil.fromJson(json, Person.class);
        assertEquals("John", person.name);
        assertEquals(30, person.age);
    }


    @Test
    void testIsValidJson() {
        String validJson = "{\"name\":\"John\",\"age\":30}";
        String invalidJson = "{\"name\":\"John\",\"age\":}";
        assertTrue(JsonUtil.isValidJson(validJson));
        assertFalse(JsonUtil.isValidJson(invalidJson));
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
