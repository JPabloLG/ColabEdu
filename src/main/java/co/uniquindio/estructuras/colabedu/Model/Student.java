package co.uniquindio.estructuras.colabedu.Model;

public class Student extends User{

    private String instituto;
    private LinkedList<Message> messageLinkedList;

    private LinkedList<StudyGroup> studyGroupLinkedList;

    private PriorityQueue<HelpRequest> helpRequestPriorityQueue;

    private BinarySearchTree<Content> contentBinarySearchTree;



    public Student(String instituto) {
        this.instituto = instituto;
    }

    public Student(String name, String email, String id, String password, String instituto) {
        super(name, email, id, password);
        this.instituto = instituto;
    }

    public Student() {
    }

    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    @Override
    public String toString() {
        return "Student{" +
                "instituto='" + instituto + '\'' +
                ", messageLinkedList=" + messageLinkedList +
                ", studyGroupLinkedList=" + studyGroupLinkedList +
                ", helpRequestPriorityQueue=" + helpRequestPriorityQueue +
                '}';
    }

    @Override
    public void updateContent() {

    }

    @Override
    public void deleteContent() {

    }

    @Override
    public void publishContent() {

    }
}
