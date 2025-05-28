package co.uniquindio.estructuras.colabedu.Model;
/**
 * A generic priority queue implemented using a sorted linked list.
 * Elements are ordered based on their natural ordering (must implement Comparable).
 * @param <T> The type of elements stored in the queue.
 */
public class PriorityQueue<T extends Comparable<T>> {

    private Node<T> head;
    private int size;

    /**
     * Default constructor. Initializes an empty priority queue.
     */
    public PriorityQueue() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts an element into the queue based on its priority.
     * Lower values are considered higher priority (min-priority queue).
     * @param data The element to insert.
     */
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty() || data.compareTo(head.getDate()) < 0) {
            newNode.setNextNode(head);
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNextNode() != null &&
                    data.compareTo(current.getNextNode().getDate()) >= 0) {
                current = current.getNextNode();
            }
            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);
        }
        size++;
    }

    /**
     * Removes and returns the element with the highest priority (lowest value).
     * @return The element with the highest priority.
     * @throws RuntimeException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("PriorityQueue is empty");
        }

        T data = head.getDate();
        head = head.getNextNode();
        size--;
        return data;
    }

    /**
     * Retrieves, but does not remove, the element with the highest priority.
     * @return The element with the highest priority.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("PriorityQueue is empty");
        }
        return head.getDate();
    }

    /**
     * Checks whether the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Gets the number of elements in the queue.
     * @return The size of the queue.
     */
    public int getSize() {
        return size;
    }
}