import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Represents a playlist that holds a collection of songs.
 */
public class Playlist implements Iterable<Song>,Cloneable,FilteredSongIterable,OrderedSongIterable {
    private ArrayList<Song> songs;
    private ArrayList<Song> filteredSongs;

    /**
     * Constructs an empty playlist.
     */
    public Playlist() {
        this.songs = new ArrayList<Song>();
        this.filteredSongs = new ArrayList<Song>();
    }
    /**
     * Adds a song to the playlist.
     *
     * @param newSong the song to be added
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song newSong){
        if ( songs.size() == 0 ) {
            songs.add(newSong);
            filteredSongs.add(newSong);
            return;
        }
        for ( Song song : songs ){
            if ( song.equals(newSong) )
                throw new SongAlreadyExistsException();
        }
        songs.add( newSong );
        filteredSongs.add( newSong );
    }
    /**
     * Removes a song from the playlist.
     *
     * @param removedSong the song to be removed
     * @return true if the song was found and removed, false otherwise
     */
    public boolean removeSong(Song removedSong) {
        boolean isHere = false;
        for ( Song song : songs ){
            if ( song.equals(removedSong) ){
                isHere = true;
                break;
            }
        }
        if ( isHere ){
            songs.remove( (Song) removedSong );
            filteredSongs.remove( (Song) removedSong );
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
        Playlist copy = new Playlist();
        for ( Song song : this.songs ){
            try {
                copy.songs.add( song.clone() );
                copy.filteredSongs.add( song.clone() );
            } catch (Exception e) {
                return null;
            }
        }
        return copy;
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
        if (songs.size() != otherPlaylist.songs.size()) {
            return false;
        }

        int i = 0;
        for (Song song : songs) {
            for (Song otherSong : otherPlaylist.songs) {
                if (song.equals(otherSong)) {
                    i += 1;
                }
            }
        }
        return i == songs.size();
    }
    /**
     * Returns a string representation of the playlist.
     *
     * @return a string representation of the playlist
     */
    @Override
    public String toString() {
        int counter =1;
        String result = "[";
        for ( Song song : songs ){
            result += "(" + song.toString() + ")";
            if(counter!=songs.size()){
                result+=", ";
            }
            counter++;
        }
        result += "]";
        return result;
    }
    /**
     * Returns a string representation of the filtered songs in the playlist.
     *
     * @return a string representation of the filtered songs
     */
    public String STRfiltered() {
        String result = "[";
        for ( Song song : filteredSongs ){
            result += "(" + song.toString() + "), ";
        }
        result += "]";
        return result;
    }
    /**
     * Filters the playlist by the artist name.
     *
     * @param artist the artist name to filter by
     */
    @Override
    public void filterArtist(String artist) {
        if ( artist != null ) {
            for (Song song : songs) {
                if ( ! song.getArtist().equals(artist) )
                    filteredSongs.remove(song);
            }
        }
    }
    /**
     * Filters the playlist by the song duration.
     *
     * @param
     */
    @Override
    public void filterDuration(int duration) {
        for (Song song : songs) {
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
        if ( genre != null ) {
            for (Song song : songs) {
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
    public void setScanningOrder( ScanningOrder sc ) {
        switch ( sc ){
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
            //System.out.println(" ENTERED HASNEXT---------------------");
            if ( index >= filteredSongs.size() ){
                //System.out.println("RESSTING filtered....");
                filteredSongs = new ArrayList<>(songs);
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
            //System.out.println(" ENTERED NEXT---------------------");
            Song val = (Song) filteredSongs.get( index );
            index += 1;
            return val;
        }
    }
}
