import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Represents a playlist that made out of songs, has two arraylist one for the playlist and the second one
 * is a copy of the playlist that is used for filter methods.
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
     * adds a song to the playlist.
     * @param newSong a song we want to add
     * @throws SongAlreadyExistsException if the song already in our playlist
     */
    public void addSong(Song newSong) {
        /** if the playlist size is 0 add the song automatically */
        if (playlist.size() == 0) {
            playlist.add(newSong);
            filteredSongs.add(newSong);
            return;
        }
        /** check the playlist if the song is already there */
        for (Song song : playlist) {
            if (song.equals(newSong))
                throw new SongAlreadyExistsException();
        }
        playlist.add(newSong);
        filteredSongs.add(newSong);
    }

    /**
     * removes a song from the playlist.
     * @param removedSong the song we want to remove
     * @return true if the song was found and removed, false otherwise
     */
    public boolean removeSong(Song removedSong) {
        boolean i = false;
        for (Song song : playlist) {
            if (song.equals(removedSong)) {
                i = true;
                break;
            }
        }
        if (i) {
            playlist.remove(removedSong);
            filteredSongs.remove(removedSong);
        }
        return i;
    }

    /**
     * makes a deep copy of the playlist
     * @return deep copy of the playlist
     */
    @Override
    public Playlist clone() {
        try {
            /** make a shallow copy */
            Playlist copy = (Playlist) super.clone();
            copy.playlist = (ArrayList<Song>) this.playlist.clone();
            copy.filteredSongs = new ArrayList<>();
            /** set each song in the copy as a deep copy of the original song */
            for (int i = 0; i < copy.playlist.size(); i++) {
                copy.playlist.set(i, copy.playlist.get(i).clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    /**
     * override of hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (Song song : this) {
            result += song.hashCode();
        }
        return result;
    }

    /**
     * checks if two playlist are equal by calling the equal method in song
     * @param other the other playlist
     * @return true if the both playlists are equals, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        /** if the sizes are different they don't have the same songs */
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
     * makes a string of the playlist.
     * @return a string of the playlist
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
     * filters the playlist by the artist name
     * @param artist the artist name to filter the songs
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
     * filters the playlist by the song duration
     * @param duration the duration to filter the songs
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
     * filters the playlist by the song genre
     * @param genre the genre to filter the songs
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
     * makes the scanning order of the playlist
     * @param order the scanning order
     */
    @Override
    public void setScanningOrder(ScanningOrder order) {
        switch (order) {
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
     * makes an iterator over the songs in the playlist
     * @return an iterator over the playlist
     */
    public Iterator<Song> iterator() {
        return new PlaylistIterator<Song>();
    }

    /**
     * an inner class for playlist iterator
     * @param <Song>
     */
    class PlaylistIterator<Song> implements Iterator<Song> {
        /** Represents an iterator for the playlist */
        private int index;

        /** Constructor */
        public PlaylistIterator() {
            this.index = 0;
        }

        /**
         * checks if there is a next song in the playlist
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
         * returns the next song in the playlist
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
