package demo;

public class MyHashTable {

    int _capacity;
    String[] _keys; // string keys only
    Object[] _values; // store any value type as Object

    public MyHashTable(int capacity) {
        if (capacity < 16)
            capacity = 16;
        this._capacity = capacity;
        this._keys = new String[capacity];
        this._values = new Object[capacity];
    }

    public int indexFor(String key) {
        long hash = 0L; // avoid overflow
        for (int i = 0; i < key.length(); i++) {
            hash = ((hash << 5) - hash) + key.charAt(i); // hash * 31 + c
        }
        // map to [0, capacity)
        return (int) Math.floorMod(hash, _capacity);
    }

    public Object get(String key) {
        // calculate the primary index
        int idx = indexFor(key);

        for (int probe = 0; probe < _capacity; probe++) {
            // find the index into the array
            int i = (idx + probe) % _capacity;

            if (_keys[i] == null) {
                return null;
            }

            if (_keys[i].equals(key)) {
                return _values[i];
            }
        }
        return null;
    }

    // Insert or update; key is not null
    public void put(String key, Object value) {
        // calculate the primary index

        // calculate the index given the primary index and offset value
        // you fill in here

        // if it is a new key, add it to the key and values array
        // you fill in here

        // otherwise, update the value associated with the key
        // you fill in here

        

    }

    public static void main(String[] args) {
        // pick a prime number to reduce collisions.
        MyHashTable table = new MyHashTable(101);

        System.out.println("index is " + table.indexFor("alice"));
        System.out.println("index is " + table.indexFor("bob"));
        System.out.println("index is " + table.indexFor("carol"));
        System.out.println("index is " + table.indexFor("ecila"));
        System.out.println("index is " + table.indexFor("zakery"));
        System.out.println("index is " + table.indexFor("AaAa"));
        System.out.println("index is " + table.indexFor("BBBB"));

        table.put("alice", 10);
        table.put("bob", 20);
        table.put("carol", 30);
        table.put("ecila", 99);
        table.put("AaAa", 13);
        table.put("BBBB", 1313);

        System.out.println(table.get("alice")); // 10
        System.out.println(table.get("bob")); // 20
        System.out.println(table.get("carol")); // 30
        System.out.println(table.get("ecila")); // 99
        System.out.println(table.get("AaAa")); // 13
        System.out.println(table.get("BBBB")); // 13

        // // Overwrite existing
        table.put("alice", 42);
        System.out.println(table.get("alice")); // 42
    }
}
