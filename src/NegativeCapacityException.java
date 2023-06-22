public class NegativeCapacityException extends StackException {
    public NegativeCapacityException() {
        super("Capacity cannot be negative.");
    }
}
