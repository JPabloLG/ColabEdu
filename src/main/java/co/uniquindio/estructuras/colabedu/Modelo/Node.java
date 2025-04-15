package co.uniquindio.estructuras.colabedu.Modelo;

public class Node<T> {
    private T date;
    private Node<T> nextNode;

    public T getDate() {
        return date;
    }

    public Node(T date) {
        this.date = date;
        this.nextNode = null;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

}
