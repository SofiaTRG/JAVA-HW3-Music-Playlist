import java.util.ArrayList;
import java.util.Iterator;

// SHOULD IT BE AN ARRAYLIST OR ARRAY??

public class Playlist extends Iterable<T> implements Cloneable {
    private static ArrayList<Song> playlist;

    public Playlist(Song... songs) {
        for (Song song: songs) {
            playlist.add(song);
        }
    }

    // ???????
    public static void setPlaylist(ArrayList<Song> playlist) {
        Playlist.playlist = playlist;
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
