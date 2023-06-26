import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * ArrayStack represents a generic stack implementation using an array.
 *
 * @param <T> the type of elements stored in the stack, which must be Cloneable.
 */
public class ArrayStack<T extends Cloneable> implements Stack<T>, Iterable<T> {

    private Cloneable[] storage;    // Array to store the elements of the stack
    private int top;                // Index of the top element in the stack
    private int capacity;           // Maximum capacity of the stack

    /**
     * Constructs an ArrayStack object with the specified maximum capacity.
     *
     * @param capacity the maximum capacity of the stack
     * @throws StackException if the maximum capacity is negative
     */
    public ArrayStack(int capacity) throws StackException {
        if (capacity >= 0) {
            this.capacity = capacity;
            this.storage = (T[]) new Cloneable[capacity];
            this.top = 0;
        } else {
            throw new NegativeCapacityException();
        }
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be added
     * @throws StackOverflowException if the stack is full
     */
    @Override
    public void push(T element) {
        if (this.top == this.capacity) {
            throw new StackOverflowException();
        } else {
            this.storage[top++] = element;
        }
    }

    /**
     * Removes and returns the element from the top of the stack.
     *
     * @return the element removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (this.top == 0) {
            throw new EmptyStackException();
        } else {
            Cloneable topElement = this.storage[top-1];
            storage[top-1] = null;
            top--;
            return (T) topElement;
        }
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return (T) this.storage[this.top - 1];
        }
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return top;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (this.top == 0);
    }

    /**
     * Creates and returns a deep copy of this ArrayStack object.
     *
     * @return a deep copy of this ArrayStack object
     */
    @Override
    public ArrayStack<T> clone() {
        ArrayStack<T> copy;
        try {
            copy = (ArrayStack<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copy.storage = new Cloneable[this.capacity];
        for (int i = 0; i < this.top; i++) {
            try {
                Method method = this.storage[i].getClass().getMethod("clone", null);
                copy.storage[i] = (Cloneable) method.invoke(this.storage[i]);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException | SecurityException exception) {
                return null;
            }
        }
        return copy;
    }

    /**
     * Iterator implementation for iterating over the elements in the stack.
     */
    private class ArrayStackIterator implements Iterator<T> {
        private int current = top-1;

        /**
         * Checks if there are more elements to iterate.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return (current >= 0);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            return (T) storage[current--];
        }
    }

    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }
}
