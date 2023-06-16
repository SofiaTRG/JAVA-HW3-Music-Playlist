public class SongAlreadyExistsException extends RuntimeException{
    public SongAlreadyExistsException() {}
    public SongAlreadyExistsException(String message) {
        super(message);
    }
    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
