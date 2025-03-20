import java.util.*;

public class Assign03_02{
    public static <T> boolean stableSort(ArraySorter<T> sorter, T[] A) {
	    // Please use the given [sorter] to sort T[] A. The return value
	    // (true or false) should indicate whether the sorting done is stable
        int[] IDHashes = new int[A.length];
        for (int i = 0; i < IDHashes.length; i++) {
            IDHashes[i] = System.identityHashCode(A[i]);
        }
        System.out.println(Arrays.toString(IDHashes));
        final Set<Object> dupes = new HashSet<>();
        final Set<Object> tmp = new HashSet<>();
        final List<Integer> hashes = new ArrayList<>(List.of());
        boolean isStable = false;

        for (Object t : A) {
            if (!tmp.add(t.toString().split(":")[0])) {
                dupes.add(t.toString().split(":")[0]);
            }
        }
        //System.out.println(dupes);
        for (Object o : dupes) {
            for (T t : A) {
                if (t.toString().split(":")[0].hashCode() == o.hashCode()) {
                    //System.out.println(t);
                    hashes.add(System.identityHashCode(t));
                }
            }
        }
        //System.out.println(hashes);

        sorter.sort(A);

        if (dupes.isEmpty()) {
            System.out.println("No duplicates found, expression is assumed to be stable.");
            return true;
        }

        for (int i = 0; i < hashes.size() - 1; i++) {
            for (int j = 0; j < A.length - 1; j++) {
                //System.out.println(hashes.get(i) + ", " + System.identityHashCode(A[j]) + " | " + hashes.get(i + 1) + ", " + System.identityHashCode(A[j + 1]));
                if (hashes.get(i) == System.identityHashCode(A[j]) && hashes.get(i + 1) == System.identityHashCode(A[j + 1])) {
                    isStable = true;
                }
            }
        }

        return isStable;
    }
    public static void main(String[] args){
        var sorter = new ArraySorter<>() {
            @Override
            public void sort(Object[] A) {
                int len = A.length;
                for (int i = 0; i < len; i++) {
                    Object key = A[i];
                    int j = i - 1;
                    while ((j >= 0) && (A[j].toString().split(":")[0].hashCode() > key.toString().split(":")[0].hashCode())) {
                        A[j + 1] = A[j];
                        j--;
                    }
                    A[j + 1] = key;
                }
            }
        };

        ObjectID[] objects = {
                new ObjectID(10, 0),
                new ObjectID(9, 0),
                new ObjectID(9, 1),
                new ObjectID(8, 0),
                new ObjectID(7, 0),
                new ObjectID(6, 0),
                new ObjectID(5, 0),
                new ObjectID(5, 1),
                new ObjectID(4, 0),
                new ObjectID(3, 0),
                new ObjectID(2, 0),
                new ObjectID(1, 0),
        };

        System.out.println(Arrays.toString(objects));
        Collections.shuffle(Arrays.asList(objects));
        System.out.println(Arrays.toString(objects));
        System.out.println(stableSort(sorter, objects));
        System.out.println(Arrays.toString(objects));
    }
}

class ObjectID implements Comparable<ObjectID>{
    private final Object obj;
    private final int id;

    ObjectID(Object obj, int id) {
        this.obj = obj;
        this.id = id;
    }

    @Override
    public int compareTo(ObjectID o) {
        return this.obj.toString().compareTo(o.obj.toString());
    }

    @Override
    public String toString() {
        return this.obj.toString() + ":" + this.id;
    }
}
