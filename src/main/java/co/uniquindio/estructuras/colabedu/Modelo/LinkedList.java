package co.uniquindio.estructuras.colabedu.Modelo;

public class LinkedList <T>{
    private Node<T> head;
    private int size;

    //Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    //Getters y Setters
    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //Method to add an element to the LinkedList
    public void add(T date){
        Node<T> newNode = new Node<T>(date);
        if (isEmpty()) {
            head = newNode;
        }else{
            newNode.setNextNode(head);
            head = newNode;
        }
        size++;
    }
    //Verify if the LinkedList is empty
    public boolean isEmpty(){
        return head == null;
    }

}
