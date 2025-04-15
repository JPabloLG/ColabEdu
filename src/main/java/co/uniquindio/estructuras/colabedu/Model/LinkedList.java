package co.uniquindio.estructuras.colabedu.Model;

/**
 * Generic implementation of a singly linked list.
 * @param <T> The type of data to be stored in the linked list.
 */
public class LinkedList<T> {
    private Node<T> head;
    private int size;

    /**
     * Default constructor.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Gets the head node of the linked list.
     * @return The head node.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the head node of the linked list.
     * @param head The node to set as the new head.
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Gets the current size of the linked list.
     * @return The number of elements in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the linked list.
     * @param size The new size value.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Adds an element to the beginning of the linked list.
     * @param data The data to be added.
     */
    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.setNextNode(head);
            head = newNode;
        }
        size++;
    }

    /**
     * Checks if the linked list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Deletes the first occurrence of the specified element from the list.
     * @param data The data to be deleted.
     * @throws RuntimeException If the list is empty.
     */
    public void delete(T data) {
        if (isEmpty()) {
            throw new RuntimeException("Cannot delete from an empty list");
        }

        if (head.getDate() == data) {
            head = head.getNextNode();
            size--;
            return;
        }

        Node<T> currentNode = head;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getDate() == data) {
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                size--;
                return;
            }
            currentNode = currentNode.getNextNode();
        }
    }

    /**
     * Searches for an element in the list.
     * @param data The data to search for.
     * @return true if the element is found, false otherwise.
     */
    public boolean searchElement(T data) {
        if (isEmpty()) {
            return false;
        }

        Node<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.getDate() == data) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }

        return false; // Element not found
    }
}

