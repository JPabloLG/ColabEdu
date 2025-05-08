package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Student extends User{

    private String instituto;

    private LinkedList<Message> messageLinkedList;

    private LinkedList<StudyGroup> studyGroupLinkedList;

    private PriorityQueue<HelpRequest> helpRequestPriorityQueue;


    //Hay que cambiar la linked list a BinarySearchTree
    private LinkedList<Content> contentBinarySearchTree;


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
    public void updateContent(Content content) {
        for (Content i:contentBinarySearchTree) {
            if(i.equals(content)){
                i.setName(content.getName());
                i.setDescription(content.getDescription());
                i.setPublicationDate(content.getPublicationDate());
                break;
            }

        }

    }

    @Override
    public void deleteContent(Content content) {
        contentBinarySearchTree.delete(content);
    }

    @Override
    public void publishContent(Content content) {
        contentBinarySearchTree.add(content);
    }

    public void createContent(String name, LocalDateTime publicationDate, String typeContent,
                              String description, String subject, Rating theRating){
        Content content = new Content(name,publicationDate, typeContent, description, subject, this, theRating);
        contentBinarySearchTree.add(content);
    }

    public void requestHelp(){}
    public List<Content> searchContent(String keyword) {
        return contentBinarySearchTree.search(keyword);
    }

    // Valorar contenido educativo
    public void rateContent(Content content, int ratingValue) {
        content.getTheRating();
        for (Rating rating : content.getTheRating()) {
            if (rating.getRatings().isEmpty()) {
                rating.addRating(ratingValue);
                break;

            }
        }
    }
            // Ver sugerencias de compañeros con intereses similares
    public List<Student> getSuggestedPeers () {
                // Implementar lógica para obtener estudiantes con intereses similares
        return List.of();
    }

            // Participar en grupos de estudio sugeridos automáticamente
    public void joinSuggestedStudyGroups (int numberOfGroups) {
                // Implementar lógica para unirse a grupos sugeridos
    }

    public void sendMessage (Student recipient, String messageContent){
        Message message = new Message( messageContent, LocalDateTime.now());
        recipient.messageLinkedList.add(message);
    }

}
