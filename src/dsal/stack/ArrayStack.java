package dsal.stack;

public class ArrayStack<T> implements Stack<T> {
    private T[] array;
    private int top;
    private int capacity;
    
    /**
     * Constructor to initialize the stack with a given capacity.
     * @param capacity the maximum number of elements the stack can hold
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    /**
     * Pushes an item onto the top of the stack.
     * @param item the item to be pushed onto the stack
     */
    public void push(T item) {
        if (top >= capacity - 1) {
            throw new IllegalStateException("Stack overflow");
        }
        array[++top] = item;
    }
    
    /**
     * Removes and returns the item at the top of the stack.
     * @return the item at the top of the stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }
        T item = array[top];
        array[top--] = null; // Help GC
        return item;
    }
    
    /**
     * Returns the item at the top of the stack without removing it.
     * @return the item at the top of the stack
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[top];
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return top + 1;
    }
}
