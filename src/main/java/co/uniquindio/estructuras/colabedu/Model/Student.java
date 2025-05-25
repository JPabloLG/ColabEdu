package co.uniquindio.estructuras.colabedu.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.time.LocalDateTime;
import java.util.List;


public class Student extends User {

    private String instituto;
    private LinkedList<Message> messageLinkedList;
    private LinkedList<StudyGroup> studyGroupLinkedList;
    private PriorityQueue<HelpRequest> helpRequestPriorityQueue;
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
        for (Content i:contentBinarySearchTree) {
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
        contentBinarySearchTree.remove(content);
    }

    @Override
    public void publishContent(Content content) {
        contentBinarySearchTree.add(content);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", instituto='" + instituto + '\'' +
                ", friends=" + friends.stream().map(Student::getName).toList() +
                '}';
    }
    public void createContent(String name, LocalDateTime publicationDate, String typeContent,
                              String description, String subject, ArrayList<Rating> theRating){
        //Content content = new Content(name, publicationDate, typeContent, description, subject, this, theRating);
        //contentBinarySearchTree.add(content);
    }

    public void requestHelp(){}
    public List<Content> searchContent(String keyword) {
        //return contentBinarySearchTree.search(keyword);
        return  null;
    }

    // Clase interna para el grafo
    public static class GraphNode {
        private String id;
        private String label;
        private double size;

        public GraphNode(String id, String label, double size) {
            this.id = id;
            this.label = label;
            this.size = size;
        }

        public String getId() { return id; }
        public String getLabel() { return label; }
        public double getSize() { return size; }
    }

    public GraphNode toGraphNode() {
        return new GraphNode(this.getId(), this.getName(), calculateNodeSize());
    }

    private double calculateNodeSize() {
        double baseSize = 30.0;
        double activityFactor = 0.5 * messageLinkedList.size() +
                0.3 * studyGroupLinkedList.size() +
                0.2 * contentBinarySearchTree.size();
        return Math.min(baseSize + activityFactor, 60.0);
    }
}