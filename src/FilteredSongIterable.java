public interface FilteredSongIterable extends Iterable<Song>{
    void filterArtist();
    void filterDuration();
    void filterGenre();
}
