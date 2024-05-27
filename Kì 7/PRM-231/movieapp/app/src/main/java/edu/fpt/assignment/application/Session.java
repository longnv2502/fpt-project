package edu.fpt.assignment.application;

import java.util.HashMap;
import java.util.Map;

public class Session{

    private static final Map<String, Object> store = new HashMap<>();

    public static void add(String key, Object value) {
        store.put(key, value);
    }

    public static Object get(String key) {
        return store.get(key);
    }

    public static Object remove(String key) {
        return store.remove(key);
    }
}
