package design.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyHashMapTests {

    @Test
    public void testPut_whenKeyNull_throwException() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyHashMap<String, String> map = new MyHashMap<>();
            map.put(null, "test-string");
        });
    }

    @Test
    public void testPut_whenKeyValid_addToMap() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");

        assertEquals("test-value", map.get("test-key"));
        assertEquals(1, map.size());
    }

    @Test
    public void testPut_whenKeyExists_UpdateValue() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");

        assertEquals("test-value", map.get("test-key"));
        assertEquals(1, map.size());

        String oldValue = map.put("test-key", "test-value-2");
        assertEquals("test-value",oldValue);

        assertEquals("test-value-2", map.get("test-key"));
        assertEquals(1, map.size());
    }

    @Test
    public void testPut_whenValueNull_addToMap() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", null);

        assertNull(map.get("test-key"));
        assertEquals(1, map.size());
    }

    @Test
    public void testGet_whenKeyNull_throwException() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyHashMap<String, String> map = new MyHashMap<>();
            map.get(null);
        });
    }

    @Test
    public void testGet_whenKeyExists_shouldReturnValue() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");

        assertEquals("test-value", map.get("test-key"));
    }

    @Test
    public void testGet_whenKeyDoesNotExists_returnNull() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");

        assertNull(map.get("test"));
    }

    @Test
    public void testRemove_whenKeyNull_throwException() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyHashMap<String, String> map = new MyHashMap<>();
            map.get(null);
        });
    }

    @Test
    public void testRemove_whenKeyExists_removeFromMap() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");

        assertEquals(1, map.size());
        assertEquals("test-value", map.remove("test-key"));
        assertEquals(0, map.size());
    }

    @Test
    public void testRemove_whenKeyDoesNotExists_shouldReturnNull() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");
        assertNull(map.remove("test"));
    }

    @Test
    public void testSize_whenPut_incrementSize() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");
        assertEquals(1, map.size());

        map.put("test", "test-value-2");
        assertEquals(2, map.size());
    }

    @Test
    public void testSize_whenUpdateKey_doNotIncrementSize() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");
        assertEquals(1, map.size());

        map.put("test-key", "test-value-2");
        assertEquals(1, map.size());
    }

    @Test
    public void testSize_whenRemove_decrementSize() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("test-key", "test-value");
        assertEquals(1, map.size());

        map.remove("test-key");
        assertEquals(0, map.size());
    }
}
