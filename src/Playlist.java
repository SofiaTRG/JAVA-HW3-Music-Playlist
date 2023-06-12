import java.util.ArrayList;
import java.util.Iterator;

// SHOULD IT BE AN ARRAYLIST OR ARRAY??

public class Playlist implements Cloneable, Iterable<Song> {

    // NU MA
    private ArrayList<Song> playlist;

    public Playlist(Song... songs) {
        playlist = new ArrayList<Song>(songs.length);
        for (Song song: songs) {
            playlist.add(song);
        }
    }

    public void addSong() {

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
