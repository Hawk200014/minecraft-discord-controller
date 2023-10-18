package de.hawk200014;

import java.util.HashMap;

public class Singletons {
    private static Singletons singletons;
    public static Singletons getInstance(){
        return singletons;
    }
    private HashMap<Object, String> singletonObjects;

    public Singletons(){
        singletonObjects = new HashMap<>();
        Singletons.singletons = this;
    }

    public void addSingleton(Object o, String key){
        singletonObjects.put(o, key);
    }

    public Object getSingleton(String key){
        if(singletonObjects.containsKey(key)) {
            return singletonObjects.get(key);
        }
        throw new IllegalArgumentException("The Key is not in the Map");
    }



}
