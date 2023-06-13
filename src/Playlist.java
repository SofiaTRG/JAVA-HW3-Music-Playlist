import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Playlist implements Cloneable, Iterable<Song> {
    private static ArrayList<Song> playlist;

    public Playlist(Song... songs) {
        playlist = new ArrayList<>(songs.length);
        playlist.addAll(Arrays.asList(songs));
    }

    /**
     * 
     * @param song
     */
    public void addSong(Song song) {
        try {
            if (!playlist.contains(song)) {
                playlist.add(song);
            } else {
                throw new SongAlreadyExistsException();
            }
        } catch (SongAlreadyExistsException e) {
            throw e;
        }
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
        return playlist.remove(song);
    }

    public void setScanningOrder(ScanningOrder order) {
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(playlist.iterator());
    }

    public void filterArtist(String artist) {

    }

    public void filterDuration(int duration) {
    }

    public void filterGenre(Song.Genre genre) {
    }


    private static class PlaylistIterator implements Iterator<Song> {
        private int currentIndex;

        public PlaylistIterator() {
            currentIndex = 0;
        }

        @Override
        public Song next() {
            if (hasNext()) {
                Song song = playlist.get(currentIndex);
                currentIndex++;
                return song;
            }
            return null; // or throw NoSuchElementException
        }

        @Override
        public boolean hasNext() {
            return currentIndex < playlist.size();
        }

    }

}
