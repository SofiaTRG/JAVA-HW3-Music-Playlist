/**
 * This class symbolise one song. it has name, artist, genre and duration
 */
// HOW TO MAKE THE DURETION INTO MM:SS??
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

    public void setDuration(int i) {
        duration = i;
    }

    /**
     * an enum class for genre. has 4 possible values: POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
     */
    public enum Genre {
        POP, ROCK, HIP_HOP, COUNTRY, JAZZ, DISCO
    }
}
