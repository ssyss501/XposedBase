package  com.example.xposedbase.hook.KktDatabase;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class f<K, V> {
    public int createCount;
    public int evictionCount;
    public int hitCount;
    public final LinkedHashMap<K, V> map;
    public int maxSize;
    public int missCount;
    public int putCount;
    public int size;

    public f(int i) {
        if (i > 0) {
            this.maxSize = i;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    private int safeSizeOf(K k2, V v) {
        int sizeOf = sizeOf(k2, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException("Negative size: " + k2 + "=" + v);
    }

    public V create(K k2) {
        return null;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public void entryRemoved(boolean z, K k2, V v, V v2) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final V get(K k2) {
        if (k2 != null) {
            synchronized (this) {
                V v = this.map.get(k2);
                if (v != null) {
                    this.hitCount++;
                    return v;
                }
                this.missCount++;
            }
        } else {
            throw new NullPointerException("key == null");
        }
        return null;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final V put(K k2, V v) {
        V put;
        if (k2 == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k2, v);
            put = this.map.put(k2, v);
            if (put != null) {
                this.size -= safeSizeOf(k2, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k2, put, v);
        }
        trimToSize(this.maxSize);
        return put;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final V remove(K k2) {
        V remove;
        if (k2 != null) {
            synchronized (this) {
                remove = this.map.remove(k2);
                if (remove != null) {
                    this.size -= safeSizeOf(k2, remove);
                }
            }
            if (remove != null) {
                entryRemoved(false, k2, remove, (V) null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    public void resize(int i) {
        if (i > 0) {
            synchronized (this) {
                this.maxSize = i;
            }
            trimToSize(i);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final synchronized int size() {
        return this.size;
    }

    public int sizeOf(K k2, V v) {
        return 1;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i;
        i = this.hitCount + this.missCount;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i != 0 ? (this.hitCount * 100) / i : 0)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    public void trimToSize(int i) {
//        Object key;
//        Object value;
//        while (true) {
//            synchronized (this) {
//                if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
//                } else if (this.size > i) {
//                    if (this.map.isEmpty()) {
//                        break;
//                    }
//                    Map.Entry next = this.map.entrySet().iterator().next();
//                    key = next.getKey();
//                    value = next.getValue();
//                    this.map.remove(key);
//                    this.size -= safeSizeOf(key, value);
//                    this.evictionCount++;
//                }
//            }
//            entryRemoved(true, key, value, (Object) null);
//        }
    }
}



