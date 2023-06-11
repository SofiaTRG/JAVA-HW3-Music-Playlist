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
     * checks if two objects are equals.
     * @param obj the second object
     * @return false if the objects are from different classes, same if they are from the same class
     * and have a same hashcode/string
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else if (hashCode() == obj.hashCode()) {
            return true;
        } //else if (toString().equals(obj.toString())) {
        return false;
    }

    /**
     * an enum class for genre. has 4 possible values: POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
     */
    public enum Genre {
        POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
    }
}
