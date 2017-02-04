package ex13;

public class HashChain {
    private static class MyKey {
        String key;
        String data;
        MyKey next;

        MyKey(String key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    private MyKey[] table;
    private int bucketSize;
    private int n;
    private static final int BUCKET_SIZE = 10;

    private HashChain() {
        this(BUCKET_SIZE);
    }

    private HashChain(int bucketSize) {
        // ここを作る
        table = new MyKey[bucketSize];
        this.bucketSize = bucketSize;
        n = 0;
    }

    private int hash(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            sum += (int) s.charAt(i);
        }
        return sum % bucketSize;
    }

    private String search(String key) {
        // ここを作る
        for (MyKey p = table[hash(key)]; p != null; p = p.next) {
            if (key.equals(p.key)) {
                return p.data;
            }
        }
        return null;
    }

    public boolean add(String key, String data) {
        // ここを作る
        if (search(key) != null) {
            System.out.println("失敗");
            return false;
        }
        MyKey p = new MyKey(key, data);
        int h = hash(key);
        p.next = table[h];
        table[h] = p;
        n++;
        return true;
    }

    public boolean remove(String key) {
        // ここを作る
        int h = hash(key);
        if (table[h] == null) {
            return false;
        }
        if (key.equals(table[h].key)) {
            MyKey p = table[h];
            table[h] = p.next;
            n--;
            return true;
        }
        MyKey p;
        MyKey q;
        for (q = table[h], p = q.next; p != null; q = p, p = p.next) {
            if (key.equals(p.key)) {
                q.next = p.next;
                n--;
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < table.length; i++) {
            s += "bucket" + i + ":";
            for (MyKey p = table[i]; p != null; p = p.next) {
                s += "[" + p.key + ":" + p.data + "] ";
            }
            s += "\n";
        }
        s += "要素数:" + n + "\n";
        return s;
    }

    public static void main(String[] args) {
        HashChain bucket = new HashChain();

        bucket.add("one", "one1");
        bucket.add("two", "two2");
        bucket.add("three", "three3");
        bucket.add("four", "four4");
        bucket.add("five", "five5");
        bucket.add("six", "six6");
        bucket.add("seven", "seven7");

        System.out.println(bucket.toString());

        System.out.print("key:fiveを探索: ");
        System.out.println(bucket.search("five"));
        System.out.println();

        System.out.println("key:threeを削除");
        if (bucket.remove("three")) {
            System.out.println(bucket.toString());
        }

        System.out.print("key:threeを探索: ");
        System.out.println(bucket.search("three"));
        System.out.println();

        bucket.add("eight", "eight8");
        bucket.add("nine", "nine9");
        bucket.add("ten", "ten10");

        System.out.println(bucket.toString());

        System.out.println("key:nineを削除");
        if (bucket.remove("nine")) {
            System.out.println(bucket.toString());
        }
    }
}
