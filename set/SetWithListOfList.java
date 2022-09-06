import java.util.*;

public class MyHasSet {
    private final int MAX_VALUE = 1000000;
    private final int ARRAY_SIZE = 100;
    private List<List<Integer>> parentList;

    public MyHasSet() {
        parentList = new ArrayList<>(ARRAY_SIZE);
        for (int i = 0; i < ARRAY_SIZE; i++) {
            parentList.add(null);
        }
    }

    private int getBucket(int key) {
        return key % ARRAY_SIZE;
    }

    public void add(int key) {
        int index = getBucket(key);
        List<Integer> childList = parentList.get(index);
        if (childList == null) {
            List<Integer> list = new LinkedList<>();
            list.add(key);
            parentList.set(index, list);
        } else {
            if (childList.contains(key)) {
                childList.add(key);
            }
        }
    }

    public void remove(int key) {
        int index = getBucket(key);
        List<Integer> childList = parentList.get(index);
        if (childList != null) {
            // Remove the particular value rather than remove the value from a index
            childList.remove(Integer.valueOf(key));
        }
    }

    public boolean contains(int key) {
        int index = getBucket(key);
        List<Integer> childList = parentList.get(index);
        return childList != null && childList.contains(key);
    }

    // Time and space complexity:
    // Depends on the table size and no.of buckets we have
}
