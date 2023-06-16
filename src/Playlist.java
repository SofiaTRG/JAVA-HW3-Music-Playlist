import java.util.*;


public class Playlist implements Cloneable, Iterable<Song> , FilteredSongIterable, OrderedSongIterable{
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
    public Playlist clone() {
        try {
            Playlist copy = (Playlist) super.clone();
            copy.playlist = new ArrayList<>(this.playlist);
            for (int i = 0; i < copy.playlist.size(); i++) {
                copy.playlist.set(i, copy.playlist.get(i).clone());
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


    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    @Override
    public Playlist filterArtist(String artist) {
        if (artist == null) {
            return this;
        }
        ArrayList<Song> filteredList = new ArrayList<>();
        Iterator<Song> iterator = playlist.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getArtist().equals(artist)) {
                filteredList.add(song);
                iterator.remove();
            }
        }
        Playlist filteredPlaylist = new Playlist();
        filteredPlaylist.playlist = filteredList;
        return filteredPlaylist;
    }

    @Override
    public Playlist filterDuration(int duration) {
        ArrayList<Song> filteredList = new ArrayList<>();
        Iterator<Song> iterator = playlist.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getDuration() <= duration) {
                filteredList.add(song);
                iterator.remove();
            }
        }
        filteredList.sort(Comparator.comparing(Song::getDuration).reversed()); // Sort by duration in descending order
        Playlist filteredPlaylist = new Playlist();
        filteredPlaylist.playlist = filteredList;
        return filteredPlaylist;
    }

    @Override
    public Playlist filterGenre(Song.Genre genre) {
        if (genre == null) {
            return this;
        }
        ArrayList<Song> filteredList = new ArrayList<>();
        Iterator<Song> iterator = playlist.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getGenre() == genre) {
                filteredList.add(song);
                iterator.remove();
            }
        }
        Playlist filteredPlaylist = new Playlist();
        filteredPlaylist.playlist = filteredList;
        return filteredPlaylist;
    }


    @Override
    public Playlist setScanningOrder(ScanningOrder order) {
        Playlist filteredPlaylist = new Playlist();

        switch (order) {
            case ADDING:
                return this;
            case NAME:
                ArrayList<Song> nameSortedList = new ArrayList<>(playlist);
                Collections.sort(nameSortedList, Comparator.comparing(Song::getName));
                filteredPlaylist.playlist = nameSortedList;
                break;
            case DURATION:
                ArrayList<Song> durationSortedList = new ArrayList<>(playlist);
                Collections.sort(durationSortedList, Comparator.comparing(Song::getDuration).reversed());
                filteredPlaylist.playlist = durationSortedList;
                break;
        }
        return filteredPlaylist;
    }



    @Override
    public int hashCode() {
        int result = 1;
        for (Song song : playlist) {
            result = 31 * result + song.hashCode();
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

//        if (playlist.size() != otherPlaylist.playlist.size()) {
//            return false;
//        }

        Iterator<Song> thisIterator = playlist.iterator();
        Iterator<Song> otherIterator = otherPlaylist.playlist.iterator();

        while (thisIterator.hasNext()) {
            Song thisSong = thisIterator.next();
            Song otherSong = otherIterator.next();
            if (!thisSong.equals(otherSong)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder playlistString = new StringBuilder();
        Iterator<Song> iterator = playlist.iterator();

        while (iterator.hasNext()) {
            Song thisSong = iterator.next();
            playlistString.append("(");
            playlistString.append(thisSong.toString());
            playlistString.append(")");

            if (iterator.hasNext()) {
                playlistString.append(", ");
            }
        }

        return "[" + playlistString.toString() + "]";
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
