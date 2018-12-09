/**
 * A simple pair container.
 * @param <K> first a value
 * @param <L> second another value
 */
public class Pair<K, L> {
    public final K first;
    public final L second;

    public Pair(K first, L second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append(first + ":" + second);
        return out.toString();
    }
}