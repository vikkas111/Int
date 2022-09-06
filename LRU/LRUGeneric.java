import java.util.*;

public class LRU<K, V> {

    class Node<K, V> {
            K key;
            V value;
            Node<K, V> next;
            Node<K, V> prev;
    }
    
    Map<K,Node<K,V>> map = new HashMap<K, Node<K, V>>();
    int capacity;
    Node<K, V> head = new Node();
    Node<K, V> tail = new Node();

    public LRU(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        head.next.prev = node;
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
        
    }
    

 public static void main(String[] args) {
      
        int capacity = 2;
        LRU<Integer, Integer> lru = new LRU<Integer, Integer>(capacity);
        lru.put(1, 2);
  
        Integer val = lru.get(1);
  
        System.out.print(val);
    
   }
}
