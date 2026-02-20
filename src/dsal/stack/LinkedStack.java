package dsal.stack;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;
    
    /**
     * Constructor to initialize an empty stack.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    /**
     * Pushes an item onto the top of the stack.
     * @param item the item to be pushed onto the stack
     */
    public void push(T item) {
        top = new Node<>(item, top);
        size++;
    }
    
    /**
     * Removes and returns the item at the top of the stack.
     * @return the item at the top of the stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }
        T item = top.data;
        top = top.next;
        size--;
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
        return top.data;
    }
    
    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return size;
    }
}
