import java.util.*;

public class LRUGeneric<K, V> {

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;
    }

    Map<K,Node<K,V>> map;
    int capacity;
    Node<K, V> head;
    Node<K, V> tail;

    public LRUGeneric(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }


    public void put(K k, V v) {
        Node node = map.get(k);
        if (node != null) {
            remove(node);
            node.value = v;
            add(node);
        } else {
            if (map.size() == this.capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node<K, V> newNode = new Node();
            newNode.key = k;
            newNode.value = v;
            map.put(k, newNode);
            add(newNode);
        }
    }

    public V get(K k) {

        if (map.containsKey(k)) {
            Node<K, V> node = map.get(k);
            remove(node);
            add(node);
            return node.value;
        }

        return null;

    }


    public static void main(String[] args) {

        int capacity = 2;
        LRUGeneric<Integer, Integer> lru = new LRUGeneric<Integer, Integer>(capacity);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        lru.put(4,4);
        System.out.println(lru.get(1));

    }
}
