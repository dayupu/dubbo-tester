package org.dayup.fun.dt.telnet.supplier.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Cache<K, V> {

    private Map<K, V> cache = new ConcurrentHashMap<K, V>();

    public V get(K key) {
        return cache.get(key);
    }

    public V put(K key, V value) {
        return cache.put(key, value);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
    }
}
