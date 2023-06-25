import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * ArrayStack represents a generic stack implementation using an array.
 *
 * @param <E> the type of elements stored in the stack, which must be Cloneable.
 */
public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E> {

    private Cloneable[] elements;  // Array to store the elements of the stack
    private int head;              // Index of the top element in the stack
    private int maxSize;           // Maximum capacity of the stack

    /**
     * Constructs an ArrayStack object with the specified maximum size.
     *
     * @param maxSize the maximum capacity of the stack
     * @throws StackException if the maximum size is negative
     */
    public ArrayStack(int maxSize) throws StackException {
        if (maxSize >= 0) {
            this.maxSize = maxSize;
            this.elements = (E[]) new Cloneable[maxSize];
            this.head = 0;
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
    public void push(E element) {
        if (this.head == this.maxSize) {
            throw new StackOverflowException();
        } else {
            this.elements[head++] = element;
        }
    }

    /**
     * Removes and returns the element from the top of the stack.
     *
     * @return the element removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (this.head == 0) {
            throw new EmptyStackException();
        } else {
            Cloneable returnHead = this.elements[head-1];
            elements[head-1] = null;
            head--;
            return (E) returnHead;
        }
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return (E) this.elements[this.head - 1];
        }
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return head;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (this.head == 0);
    }

    /**
     * Creates and returns a deep copy of this ArrayStack object.
     *
     * @return a deep copy of this ArrayStack object
     */
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> copy;
        try {
            copy = (ArrayStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copy.elements = new Cloneable[this.maxSize];
        for (int i = 0; i < this.head; i++) {
            try {
                Method method = this.elements[i].getClass().getMethod("clone", null);
                copy.elements[i] = (Cloneable) method.invoke(this.elements[i]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                     NoSuchMethodException | SecurityException exception) {
                return null;
            }
        }
        return copy;
    }

    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * Iterator implementation for iterating over the elements in the stack.
     */
    private class StackIterator implements Iterator<E> {
        private int curr = head-1;

        /**
         * Checks if there are more elements to iterate.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return (curr >= 0);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            return (E) elements[curr--];
        }
    }
}
