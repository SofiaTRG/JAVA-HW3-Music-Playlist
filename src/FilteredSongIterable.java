/**
 * Represents an iterable collection of songs with filtering capabilities.
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * Filters the songs by artist name.
     * @param artist the artist name to filter by
     */
    public void filterArtist(String artist);

    /**
     * Filters the songs by duration.
     * @param duration the maximum duration in seconds to filter by
     */
    public void filterDuration(int duration);

    /**
     * Filters the songs by genre.
     * @param genre the genre to filter by
     */
    public void filterGenre(Song.Genre genre);
}
