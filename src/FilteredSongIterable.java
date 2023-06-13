public interface FilteredSongIterable extends Iterable<Song>{
    void filterArtist(String artist);
    void filterDuration(int duration);
    void filterGenre(Song.Genre genre);
}
