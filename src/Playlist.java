import java.util.*;

public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> originalPlaylist;

    public Playlist(Song... songs) {
        playlist = new ArrayList<>(songs.length);
        playlist.addAll(Arrays.asList(songs));
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
//        if (!playlist.contains(song)) {
//            playlist.add(song);
//        } else {
//            throw new SongAlreadyExistsException();
//        }
//    }
        if ( playlist.size() == 0 ) {
            playlist.add(song);
            return;
        }
        for ( Song currentSong : playlist ){
            if ( currentSong.equals(song) )
                throw new SongAlreadyExistsException();
        }
        playlist.add( song );
    }

    @Override
    public Playlist clone() {
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

    public boolean removeSong(Song song) {
        return playlist.remove(song);
    }

    @Override
    public Iterator<Song> iterator() {
        return playlist.iterator();
    }

    @Override
    public Playlist filterArtist(String artist) {
        Playlist filteredPlaylist = new Playlist();
        for (Song song : playlist) {
            if (song.getArtist().equals(artist)) {
                filteredPlaylist.addSong(song);
            }
        }
        return filteredPlaylist;
    }

    @Override
    public Playlist filterDuration(int duration) {
        Playlist filteredPlaylist = new Playlist();
        for (Song song : playlist) {
            if (song.getDuration() <= duration) {
                filteredPlaylist.addSong(song);
            }
        }
        return filteredPlaylist;
    }

    @Override
    public Playlist filterGenre(Song.Genre genre) {
        Playlist filteredPlaylist = new Playlist();
        for (Song song : playlist) {
            if (song.getGenre() == genre) {
                filteredPlaylist.addSong(song);
            }
        }
        return filteredPlaylist;
    }

    @Override
    public Playlist setScanningOrder(ScanningOrder order) {
        Playlist filteredPlaylist = this.clone();
        switch (order) {
            case ADDING:
                break;
            case NAME:
                Playlist filteredPlaylist = this.clone();
                filteredPlaylist.playlist.sort(Comparator.comparing(Song::getName));
                break;
            case DURATION:
                Playlist filteredPlaylist = this.clone();
                filteredPlaylist.playlist.sort(Comparator.comparing(Song::getDuration).reversed());
                break;
        }

        return filteredPlaylist;
    }


    @Override
    public int hashCode() {
        int result = 0;
        for ( Song song : this ){
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

    @Override
    public String toString() {
        StringBuilder playlistString = new StringBuilder();
        for (int i = 0; i < playlist.size(); i++) {
            playlistString.append("(");
            playlistString.append(playlist.get(i).toString());
            playlistString.append(")");
            if (i < playlist.size() - 1) {
                playlistString.append(", ");
            }
        }
        return "[" + playlistString + "]";
    }

    private class PlaylistIterator implements Iterator<Song> {
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
            return null;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < playlist.size();
        }
    }
}
