import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private List<Value> values;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.values = new ArrayList<>();
            this.values.add(value);
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public List<Value> get(Key key) {
        Node x = get(root, key);
        if (x == null) {
            return new ArrayList<>();
        } else {
            return x.values;
        }

    }

    public void delete(Key key, Value value) {
        root = delete(root, key, value);
    }

    public Iterable<Key> keys() {
        List<Key> keys = new ArrayList<>();
        inorder(root, keys);
        return keys;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }

    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.values.add(value);
        return x;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    private Node delete(Node x, Key key, Value value) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key, value);
        else if (cmp > 0) x.right = delete(x.right, key, value);
        else {
            x.values.remove(value);
            if (x.values.isEmpty()) {
                if (x.right == null) return x.left;
                if (x.left == null) return x.right;
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private void inorder(Node x, List<Key> keys) {
        if (x == null) return;
        inorder(x.left, keys);
        keys.add(x.key);
        inorder(x.right, keys);
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }
}