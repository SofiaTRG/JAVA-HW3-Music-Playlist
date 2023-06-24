import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Represents a playlist that holds a collection of songs.
 */
public class Playlist implements Iterable<Song>, Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filteredSongs;

    /**
     * Constructs an empty playlist.
     */
    public Playlist() {
        this.playlist = new ArrayList<>();
        this.filteredSongs = new ArrayList<>();
    }

    /**
     * Adds a song to the playlist.
     *
     * @param newSong the song to be added
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song newSong) {
        if (playlist.size() == 0) {
            playlist.add(newSong);
            filteredSongs.add(newSong);
            return;
        }
        for (Song song : playlist) {
            if (song.equals(newSong))
                throw new SongAlreadyExistsException();
        }
        playlist.add(newSong);
        filteredSongs.add(newSong);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param removedSong the song to be removed
     * @return true if the song was found and removed, false otherwise
     */
    public boolean removeSong(Song removedSong) {
        boolean isHere = false;
        for (Song song : playlist) {
            if (song.equals(removedSong)) {
                isHere = true;
                break;
            }
        }
        if (isHere) {
            playlist.remove(removedSong);
            filteredSongs.remove(removedSong);
        }
        return isHere;
    }

    /**
     * Creates a shallow copy of the playlist.
     *
     * @return a new Playlist object that is a shallow copy of this playlist
     */
    @Override
    public Playlist clone() {
        try {
            Playlist copy = (Playlist) super.clone();
            copy.playlist = (ArrayList<Song>) this.playlist.clone();
            copy.filteredSongs = new ArrayList<>();
            for (int i = 0; i < copy.playlist.size(); i++) {
                copy.playlist.set(i, copy.playlist.get(i).clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }



    @Override
    public int hashCode() {
        int result = 0;
        for (Song song : this) {
            result += song.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Playlist otherPlaylist = (Playlist) other;
        if (playlist.size() != otherPlaylist.playlist.size()) {
            return false;
        }

        int i = 0;
        for (Song song : playlist) {
            for (Song otherSong : otherPlaylist.playlist) {
                if (song.equals(otherSong)) {
                    i += 1;
                }
            }
        }
        return i == playlist.size();
    }

    /**
     * Returns a string representation of the playlist.
     *
     * @return a string representation of the playlist
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        builder.append("[");
        for (Song song : playlist) {
            builder.append("(").append(song.toString()).append(")");
            if (counter != playlist.size()) {
                builder.append(", ");
            }
            counter++;
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * Filters the playlist by the artist name.
     *
     * @param artist the artist name to filter by
     */
    @Override
    public void filterArtist(String artist) {
        if (artist != null) {
            for (Song song : playlist) {
                if (!song.getArtist().equals(artist))
                    filteredSongs.remove(song);
            }
        }
    }

    /**
     * Filters the playlist by the song duration.
     *
     * @param duration the duration to filter by
     */
    @Override
    public void filterDuration(int duration) {
        for (Song song : playlist) {
            if (song.getDuration() > duration) {
                filteredSongs.remove(song);
            }
        }
    }

    /**
     * Filters the playlist by the song genre.
     *
     * @param genre the genre to filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        if (genre != null) {
            for (Song song : playlist) {
                if (song.getGenre() != genre) {
                    filteredSongs.remove(song);
                }
            }
        }
    }

    /**
     * Sets the scanning order of the playlist.
     *
     * @param sc the scanning order
     */
    @Override
    public void setScanningOrder(ScanningOrder sc) {
        switch (sc) {
            case ADDING:
                break;
            case NAME:
                filteredSongs.sort(Comparator.comparing(Song::getName));
                break;
            case DURATION:
                filteredSongs.sort(Comparator.comparing(Song::getDuration));
                break;
        }
    }

    /**
     * Returns an iterator over the songs in the playlist.
     *
     * @return an iterator over the songs in the playlist
     */
    public Iterator<Song> iterator() {
        return new PlaylistIterator<Song>();
    }

    class PlaylistIterator<Song> implements Iterator<Song> {
        /**
         * Represents an iterator for the playlist.
         */
        private int index;

        /**
         * Constructs a PlaylistIterator object.
         */
        public PlaylistIterator() {
            this.index = 0;
        }

        /**
         * Checks if there is a next song in the playlist.
         *
         * @return true if there is a next song, false otherwise
         */
        @Override
        public boolean hasNext() {
            if (index >= filteredSongs.size()) {
                filteredSongs = new ArrayList<>(playlist);
                return false;
            }
            return true;
        }

        /**
         * Returns the next song in the playlist.
         *
         * @return the next song in the playlist
         */
        @Override
        public Song next() {
            Song value = (Song) filteredSongs.get(index);
            index += 1;
            return value;
        }
    }
}
