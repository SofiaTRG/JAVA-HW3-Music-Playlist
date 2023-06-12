import java.util.ArrayList;
import java.util.Iterator;

// SHOULD IT BE AN ARRAYLIST OR ARRAY??

public class Playlist extends Iterable<T> implements Cloneable {
    private static ArrayList<Song> playlist;

    public Playlist(Song... songs) {

    }
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
