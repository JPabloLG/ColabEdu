package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends User{

    private String instituto;

    private LinkedList<Message> messageLinkedList;

    private LinkedList<StudyGroup> studyGroupLinkedList;

    private PriorityQueue<HelpRequest> helpRequestPriorityQueue;

    private ArrayList<Content> contents;

    private List<String> interests;


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

    public List<String> getInterests() { return interests; }

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
        for (Content i:contents) {
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
        contents.remove(content);
    }

    @Override
    public void publishContent(Content content) {
        contents.add(content);
    }

    public void createContent(String name, LocalDateTime publicationDate, String typeContent,
                              String description, String subject, Rating theRating){
        Content content = new Content(name,publicationDate, typeContent, description, subject, this, theRating);
        contents.add(content);
    }


    public List<Content> searchContent(String keyword) {
        return contents.stream()
                .filter(content -> content.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        content.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        content.getSubject().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
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
    public List<Student> getSuggestedPeers (Student currentStudent, List<Student> allStudents) {
        List<String> myInterests = currentStudent.getInterests();

        return allStudents.stream()
                .filter(peer -> !peer.equals(currentStudent)) // evitar sugerirse a sí mismo
                .sorted((a, b) -> {
                    long commonA = a.getInterests().stream().filter(myInterests::contains).count();
                    long commonB = b.getInterests().stream().filter(myInterests::contains).count();
                    return Long.compare(commonB, commonA); // ordenar por mayor número de intereses comunes
                })
                .limit(5) // opcional: limitar la cantidad de sugerencias
                .collect(Collectors.toList());
    }

            // Participar en grupos de estudio sugeridos automáticamente
    public void joinSuggestedStudyGroups (int numberOfGroups) {
                // Implementar lógica para unirse a grupos sugeridos
    }

    public void sendMessage (Student recipient, String messageContent){
        Message message = new Message( messageContent, LocalDateTime.now());
        recipient.messageLinkedList.add(message);
    }

    public void receiveMessage(Message message) {
        this.messageLinkedList.add(message);
    }

    public void createStudyGroup(String nameGroup, String description) {
        StudyGroup studyGroup = new StudyGroup(nameGroup, description);
        this.studyGroupLinkedList.add(studyGroup);
    }
    public void joinStudyGroup(StudyGroup studyGroup) {
        this.studyGroupLinkedList.add(studyGroup);
    }
    public void leaveStudyGroup(StudyGroup studyGroup) {
        this.studyGroupLinkedList.remove(studyGroup);
    }
    public void createHelpRequest(String title, String description) {
        HelpRequest helpRequest = new HelpRequest(title, description, this);
        this.helpRequestPriorityQueue.enqueue(helpRequest);
    }
    public HelpRequest getNextHelpRequest() {
        if (!helpRequestPriorityQueue.isEmpty()) {
            return helpRequestPriorityQueue.dequeue();
        }
        return null; // o lanzar una excepción si se prefiere
    }
    public void addInterest(String interest) {
        if (this.interests == null) {
            this.interests = new ArrayList<>();
        }
        this.interests.add(interest);
    }
    public void removeInterest(String interest) {
        if (this.interests != null) {
            this.interests.remove(interest);
        }
    }
    public List<Content> getContents() {
        return contents;
    }
    public void setContents(List<Content> contents) {
        this.contents = new ArrayList<>(contents);
    }
    public LinkedList<Message> getMessageLinkedList() {
        return messageLinkedList;
    }

}