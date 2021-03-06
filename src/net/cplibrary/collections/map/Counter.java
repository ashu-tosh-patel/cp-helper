package net.cplibrary.collections.map;

/**
 * @author Ashutosh Patel (ashutoshpatelnoida@gmail.com)
 * Linkedin : ( https://www.linkedin.com/in/ashutosh-patel-7954651ab/ )
 */
public class Counter<K> extends EHashMap<K, Long> {
    public Counter() {
        super();
    }

    public Counter(int capacity) {
        super(capacity);
    }

    public long add(K key) {
        long result = get(key);
        put(key, result + 1);
        return result + 1;
    }

    public void add(K key, long delta) {
        put(key, get(key) + delta);
    }

    @Override
    public Long get(Object key) {
        if (containsKey(key)) {
            return super.get(key);
        }
        return 0L;
    }
}
