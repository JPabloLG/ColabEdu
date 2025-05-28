package co.uniquindio.estructuras.colabedu.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Student extends User {

    private String instituto;
    private LinkedList<Message> messageLinkedList;
    private LinkedList<StudyGroup> studyGroupLinkedList;
    private PriorityQueue<HelpRequest> helpRequestPriorityQueue;
    private ArrayList<Content> contents;
    private List<String> interests;
    private ArrayList<Student> friends;
    private LinkedList<Content> contentBinarySearchTree;

    public Student() {
        super();
        this.friends = new ArrayList<>();
        this.messageLinkedList = new LinkedList<>();
        this.studyGroupLinkedList = new LinkedList<>();
        this.helpRequestPriorityQueue = new PriorityQueue<>();
        this.contentBinarySearchTree = new LinkedList<>();
    }

    public Student(String name, String email, String id, String password) {
        super(name, email, id, password);
        this.instituto = instituto;
        this.friends = new ArrayList<>();
        this.messageLinkedList = new LinkedList<>();
        this.studyGroupLinkedList = new LinkedList<>();
        this.helpRequestPriorityQueue = new PriorityQueue<>();
        this.contentBinarySearchTree = new LinkedList<>();
    }

    // Métodos de gestión de amistades
    public void addFriend(Student friend) {
        if (friend == null || this.equals(friend)) return;

        if (!friends.contains(friend)) {
            synchronized (this) {
                friends.add(friend);
                if (!friend.getFriends().contains(this)) {
                    friend.addFriend(this);
                }
            }
        }
    }

    public void removeFriend(Student friend) {
        if (friends.contains(friend)) {
            synchronized (this) {
                friends.remove(friend);
                if (friend.getFriends().contains(this)) {
                    friend.removeFriend(this);
                }
            }
        }
    }

    public ArrayList<Student> getFriends() {
        return new ArrayList<>(friends);
    }

    public ArrayList<Student> getFriendsWithAffinity() {
        ArrayList<Student> friendsWithAffinity = new ArrayList<>();
        for (Student friend : friends) {
            if (hasStrongAffinity(friend)) {
                friendsWithAffinity.add(friend);
            }
        }
        return friendsWithAffinity.isEmpty() ? getFriends() : friendsWithAffinity;
    }

    private boolean checkSharedGroups(Student friend) {
        return Optional.ofNullable(this.studyGroupLinkedList)
                .map(list -> list.stream()
                        .filter(Objects::nonNull)
                        .anyMatch(group -> friend.getStudyGroupLinkedList() != null &&
                                friend.getStudyGroupLinkedList().contains(group)))
                .orElse(false);
    }

    private boolean hasStrongAffinity(Student friend) {
        return Optional.ofNullable(friend)
                .map(f -> checkSharedGroups(f) || checkMessageCount(f) > 5)
                .orElse(false);
    }

    private long checkMessageCount(Student friend) {
        return Optional.ofNullable(this.messageLinkedList)
                .map(list -> list.stream()
                        .filter(Objects::nonNull)
                        .filter(m -> friend.equals(m.getUser())) // Cambio aquí para usar getUser()
                        .count())
                .orElse(0L);
    }

    // Getters y Setters
    public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    public List<String> getInterests() { return interests; }


    public LinkedList<StudyGroup> getStudyGroupLinkedList() {
        return studyGroupLinkedList;
    }


    // Métodos de User
    @Override
    public void updateContent(Content content) {
        // Implementación según necesidades
    /*
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

    }*/
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
                              String description, String subject, Rating theRating, byte[] fileData, String fileName, String fileType){
        Content content = new Content(name,publicationDate, typeContent, description, subject, this, theRating, fileData, fileName, fileType);
        contents.add(content);
    }


    public List<Content> searchContent(String keyword) {
        return contents.stream()
                .filter(content -> content.getSubject().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
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
    public void createHelpRequest(String title,String subject, String description, int priorityLevel) {
        HelpRequest helpRequest = new HelpRequest(title, subject, description, priorityLevel);
        this.helpRequestPriorityQueue.add(helpRequest);
    }
    public HelpRequest getNextHelpRequest() {
        if (!helpRequestPriorityQueue.isEmpty()) {
            return helpRequestPriorityQueue.peek();
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

    private double calculateNodeSize() {
        double baseSize = 30.0;
        double activityFactor = 0.5 * messageLinkedList.size() +
                0.3 * studyGroupLinkedList.size() +
                0.2 * contentBinarySearchTree.size();
        return Math.min(baseSize + activityFactor, 60.0);
    }
}