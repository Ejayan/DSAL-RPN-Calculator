package dsal.stack;

public interface Stack<T> {
    /**
     * Pushes an item onto the stack.
     */
    void push(T item);
    /**
     * Pops an item off the stack.
     */
    T pop();
    /**
     * Peeks at the top item of the stack without removing it.
     */
    T peek();
    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();
    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    int size();
}
