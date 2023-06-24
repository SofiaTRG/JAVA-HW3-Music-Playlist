/**
 * This class symbolise one song. it has name, artist, genre and duration
 */
public class Song implements Cloneable {
    private String name;
    private String artist;
    private Genre genre;
    private int duration;

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
     * An override of clone(). it does a deep clone of a song.
     * @return a deep clone of the song. if the method failed to make a copy it will return null.
     */
    @Override
    public Song clone() {
        try {
            Song copy = (Song) super.clone();
            copy.name = this.name;
            copy.artist = this.artist;
            copy.genre = this.genre;
            copy.duration = this.duration;
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * @return the string of song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + stringDuration(duration);
    }
    private String stringDuration(int duration) {
        String minutes = new String(String.valueOf(duration/60));
        String seconds = String.format("%02d", duration%60);
        return minutes + ":" + seconds;
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
     * @return hashcode
     */
    @Override
    public int hashCode() {
        /** choose prime number as the initial value */
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31* result + duration;
        return result;
        }

    /**
     * checks if two songs has same name and artist
     * @param obj the second song
     * @return false if the song are different or the second song is null,
     * true if they share same name and artist
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Song otherSong = (Song) obj;

        return name.equals(otherSong.name) && artist.equals(otherSong.artist);
    }


    /**
     * set a new duration for a song
     * @param duration duration of the song
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * an enum class for genre. has 4 possible values: POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
     */
    public enum Genre {
        POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
    }
}
