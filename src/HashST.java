import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int n;
    private int m;
    private List<Entry>[] st;

    private static class Entry {
        private Object key;
        private Object val;

        public Entry(Object key, Object val) {
            this.key = key;
            this.val = val;
        }
    }

    public HashST() {
        this(INIT_CAPACITY);
    }

    public HashST(int capacity) {
        this.m = capacity;
        st = (List<Entry>[]) new List[m];
        for (int i = 0; i < m; i++)
            st[i] = new LinkedList<>();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value val) {
        if (!contains(key)) n++;
        int i = hash(key);
        for (Entry e : st[i]) {
            if (key.equals(e.key)) {
                e.val = val;
                return;
            }
        }
        st[i].add(new Entry(key, val));
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Entry e : st[i]) {
            if (key.equals(e.key)) {
                return (Value) e.val;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return n;
    }

    public Iterable<Key> keys() {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (Entry e : st[i]) {
                keys.add((Key) e.key);
            }
        }
        return keys;
    }
}