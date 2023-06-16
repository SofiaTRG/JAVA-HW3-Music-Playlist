public class SongAlreadyExistsException extends RuntimeException{
    public SongAlreadyExistsException() {
        System.out.println("Cannot add the song!");
    }
    public SongAlreadyExistsException(String message) {
        super(message);
    }
    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
