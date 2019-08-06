package lrucache;

import lrucache.model.LRU;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LRUTest {
    LRU lru;

    @Before
    public void setUp() {
        this.lru = new LRU<String, String>(5);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(this.lru.isEmpty());
    }

    @Test
    public void testLRU() {
        this.lru.putIfAbsent("/link1", "content1");
        this.lru.putIfAbsent("/link1", "content1");
        this.lru.putIfAbsent("/link2", "content2");
        Assert.assertEquals(2, this.lru.size());
        this.lru.get("/link1");
        Assert.assertEquals("/link1", this.lru.keySet().toArray()[1]);
        this.lru.get("/link2");
        Assert.assertEquals("/link2", this.lru.keySet().toArray()[1]);
        this.lru.putIfAbsent("/link3", "content3");
        this.lru.putIfAbsent("/link4", "content4");
        this.lru.putIfAbsent("/link5", "content5");
        this.lru.putIfAbsent("/link6", "content6");
        Assert.assertFalse(this.lru.containsKey("/link1"));
    }
}
