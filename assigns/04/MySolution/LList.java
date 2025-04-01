public class LList<T> {
    T elem;
    LList<T> next;

    public LList(T elem){
        this.elem = elem;
        this.next = null;
    }

    @Override
    public String toString() {
        String s = "[" + elem + ", " + next;
        if (next == null) {
          return "[" + elem + "]";
        }
        s = s.replaceAll(" \\[*", " ");
        return s;
    }
}
