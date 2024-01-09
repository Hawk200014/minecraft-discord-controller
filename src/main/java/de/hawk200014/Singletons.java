package de.hawk200014;

import java.util.HashMap;

/**
 * Singleton class for managing a registry of objects with unique keys.
 */
public class Singletons {

    /** The singleton instance. */
    private static Singletons singletons;

    /** The registry of singleton objects with unique keys. */
    private final HashMap<String, Object> singletonObjects;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private Singletons() {
        // Initialize the registry
        singletonObjects = new HashMap<>();
        // Set the singleton instance
        Singletons.singletons = this;
    }

    /**
     * Gets the singleton instance.
     *
     * @return The singleton instance.
     */
    public static synchronized Singletons getInstance() {
        // Create the instance if it doesn't exist
        if (singletons == null) {
            singletons = new Singletons();
        }
        return singletons;
    }

    /**
     * Adds a singleton object to the registry.
     *
     * @param o   The singleton object.
     * @param key The unique key associated with the object.
     */
    public void addSingleton(Object o, String key) {
        singletonObjects.put(key, o);
    }

    /**
     * Gets a singleton object from the registry based on the key.
     *
     * @param key The unique key associated with the object.
     * @return The singleton object.
     * @throws IllegalArgumentException If the key is not found in the registry.
     */
    public Object getSingleton(String key) {
        if (singletonObjects.containsKey(key)) {
            return singletonObjects.get(key);
        }
        // Throw an exception if the key is not found
        throw new IllegalArgumentException("The key '" + key + "' is not in the Map");
    }
}
