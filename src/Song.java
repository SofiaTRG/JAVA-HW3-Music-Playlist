import java.util.Objects;

/**
 * This class symbolise one song. it has name, artist, genre and duration
 */
public class Song  implements Cloneable {
    final private String name;
    final private String artist;
    final private Genre genre;
    final private int duration;

    /**
     * constructor of song class
     * @param name song name
     * @param artist the artist of the song
     * @param genre the genre of the song
     * @param duration the duration of the song
     */
    public Song(String name, String artist, Genre genre, int duration ) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * An override of clone(). it does a deep clone of a song
     * @return a deep clone of the song
     * @throws CloneNotSupportedException cannot clone the song
     */
    // do it covient return type
    @Override
    public Song clone() throws CloneNotSupportedException {
        try {
            return new Song(name, artist, genre, duration);
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * @return the string of song
     */
    @Override
    public String toString() {
        return name + artist + genre + duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * override of hashcode so we can use the equals
     * @return
     */
    // NEED TO OVERRIDE
    @Override
    public int hashCode() {
        return name.hashCode() + artist.hashCode();
    }

    /**
     * checks if two songs has same name and artist
     * @param otherSong the second song
     * @return false if the song are different or the second song is null,
     * true if they share same name and artist
     */
    public boolean equals(Song otherSong) {
        if (otherSong == null) {
            return false;
        } else return getName().equals(otherSong.getName()) && getArtist().equals(otherSong.getArtist());
    }

    /**
     * an enum class for genre. has 4 possible values: POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
     */
    public enum Genre {
        POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
    }
}
