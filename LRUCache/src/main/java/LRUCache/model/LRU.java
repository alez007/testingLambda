package LRUCache.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> extends LinkedHashMap<K, V> {
    int maxEntries;

    public LRU(int maxEntries) {
        super(Math.min(16, maxEntries), 0.5f, true);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.maxEntries;
    }
}
