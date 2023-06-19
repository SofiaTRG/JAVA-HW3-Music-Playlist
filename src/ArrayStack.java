import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E> {
    private Cloneable[] elements;
    private int head;
    private int maxSize;

    public ArrayStack(int maxSize) throws StackException {
        if (maxSize >= 0) {
            this.maxSize = maxSize;
            this.elements = new Cloneable[maxSize];
            this.head = 0;
        } else throw new NegativeCapacityException();
    }


    @Override
    public void push(E element) {
        if (this.head == this.maxSize) {
            throw new StackOverflowException();
        } else {

            this.elements[head++] = element;

        }
    }


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

    @Override
    public E peek() {
        if (this.head == 0) {
            throw new EmptyStackException();
        } else {
            return (E) this.elements[head-1];
        }

    }

    @Override
    public int size() {
        return head;
    }

    @Override
    public boolean isEmpty() {
        if (this.head == 0){
            return true;
        }
        return false;
    }

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
            return copy;
        }
    }

    private class StackIterator implements Iterator<E> {
        private int I;

        public StackIterator() {
            this.I = head - 1;
        }

        @Override
        public boolean hasNext() {
            return (I >= 0);
        }

        @Override
        public E next() {
            return (E) elements[I--];
        }
    }




    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
}