public interface FilteredSongIterable extends Iterable<Song>{
    public void filterArtist(String artist);
    public void filterDuration(int duration);
    public void filterGenre(Song.Genre genre);
}
