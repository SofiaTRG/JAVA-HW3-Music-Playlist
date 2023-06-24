/**
 * Exception thrown when a song already exists in the playlist.
 */
public class SongAlreadyExistsException extends RuntimeException {
    /**
     * Constructs a new SongAlreadyExistsException with no specified detail message.
     */
    public SongAlreadyExistsException() {}

    /**
     * Constructs a new SongAlreadyExistsException with the specified detail message.
     * @param message the detail message
     */
    public SongAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new SongAlreadyExistsException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
