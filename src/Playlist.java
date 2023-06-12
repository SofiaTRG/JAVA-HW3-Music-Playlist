import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Playlist implements Cloneable, Iterable<Song> {
    private ArrayList<Song> playlist;

    public Playlist(Song... songs) {
        playlist = new ArrayList<>(songs.length);
        playlist.addAll(Arrays.asList(songs));
    }

    /**
     * Adds a song to playlist
     * @param song the song we want to add
     */
    public void addSong(Song song) {
        playlist.add(song);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Song> iterator() {
        return playlist.iterator();
    }

    /**
     * A deep clone of playlist. makes a new empty playlist in the size of the previous one and puting a clone
     * of each song from the previous playlist to the new one.
     * @return deep copy of previous playlist
     */
    @Override
    protected Playlist clone() {
        try {
            Playlist copy = (Playlist) super.clone();
            copy.playlist = new ArrayList<>(this.playlist.size());
            for (Song song : this.playlist) {
                copy.playlist.add(song.clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * removes a song from a playlist
     * @param song the song we want to remove
     * @return true if the playlist contained the song before the remove, false otherwise
     */
    public boolean removeSong(Song song) {
        playlist.remove(song);
    }
}
