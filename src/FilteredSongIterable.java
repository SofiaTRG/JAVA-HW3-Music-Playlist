public interface FilteredSongIterable extends Iterable<Song>{
    public Playlist filterArtist(String artist);
    public Playlist filterDuration(int duration);
    public Playlist filterGenre(Song.Genre genre);
}
