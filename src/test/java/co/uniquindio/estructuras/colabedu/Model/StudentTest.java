package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Student
 * This class tests the functionality of the Student class
 */
public class StudentTest {

    private Student student;
    private Student friend;

    @BeforeEach
    public void setUp() {
        student = new Student("Test Student", "test@example.com", "123456", "password");
        friend = new Student("Test Friend", "friend@example.com", "654321", "password");
    }

    @Test
    @DisplayName("Test adding a friend")
    public void testAddFriend() {
        // Arrange
        assertFalse(student.getFriends().contains(friend));

        // Act
        student.addFriend(friend);

        // Assert
        assertTrue(student.getFriends().contains(friend));
        assertEquals(1, student.getFriends().size());
    }

    @Test
    @DisplayName("Test removing a friend")
    public void testRemoveFriend() {
        // Arrange
        student.addFriend(friend);
        assertTrue(student.getFriends().contains(friend));

        // Act
        student.removeFriend(friend);

        // Assert
        assertFalse(student.getFriends().contains(friend));
        assertEquals(0, student.getFriends().size());
    }

    @Test
    @DisplayName("Test creating and joining a study group")
    public void testCreateAndJoinStudyGroup() {
        // Arrange
        String groupName = "Test Group";
        String description = "Test Description";

        // Act
        student.createStudyGroup(groupName, description);

        // Assert
        assertNotNull(student.getStudyGroupLinkedList());
        assertEquals(1, student.getStudyGroupLinkedList().size());

        // Get the study group from the linked list
        StudyGroup studyGroup = null;
        if (!student.getStudyGroupLinkedList().isEmpty()) {
            studyGroup = student.getStudyGroupLinkedList().getFirst();
        }

        assertNotNull(studyGroup);
        assertEquals(groupName, studyGroup.getNameGroup());
        assertEquals(description, studyGroup.getDescription());
    }

    @Test
    @DisplayName("Test adding and removing interests")
    public void testAddAndRemoveInterests() {
        // Arrange
        String interest = "Programming";

        // Act - Add interest
        student.addInterest(interest);

        // Assert
        assertTrue(student.getInterests().contains(interest));

        // Act - Remove interest
        student.removeInterest(interest);

        // Assert
        assertFalse(student.getInterests().contains(interest));
    }

    @Test
    @DisplayName("Test creating and publishing content")
    public void testCreateAndPublishContent() {
        // Arrange
        String contentName = "Test Content";
        LocalDateTime publicationDate = LocalDateTime.now();
        String typeContent = "Document";
        String description = "Test Description";
        String subject = "Computer Science";
        Rating rating = new Rating();
        byte[] fileData = "Test file data".getBytes();
        String fileName = "test.txt";
        String fileType = "text/plain";

        // Initialize contents list since it's not initialized in the constructor
        student.setContents(new ArrayList<>());

        // Act
        student.createContent(contentName, publicationDate, typeContent, description,
                subject, rating, fileData, fileName, fileType);

        // Assert
        List<Content> contents = student.getContents();
        assertNotNull(contents);
        assertFalse(contents.isEmpty());

        Content content = contents.get(0);
        assertEquals(contentName, content.getName());
        assertEquals(publicationDate, content.getPublicationDate());
        assertEquals(typeContent, content.getTypeContent());
        assertEquals(description, content.getDescription());
        assertEquals(subject, content.getSubject());
        assertArrayEquals(fileData, content.getFileData());
        assertEquals(fileName, content.getFileName());
        assertEquals(fileType, content.getFileType());
    }

    @Test
    @DisplayName("Test receiving a message")
    public void testReceiveMessage() {
        // Arrange
        String messageContent = "Hello, this is a test message";
        LocalDateTime messageDate = LocalDateTime.now();
        Message message = new Message(messageDate, messageContent, friend);

        // Act
        student.receiveMessage(message);

        // Assert
        assertNotNull(student.getMessageLinkedList());
        assertEquals(1, student.getMessageLinkedList().size());

        // Get the message from the linked list
        Message receivedMessage = null;
        if (!student.getMessageLinkedList().isEmpty()) {
            receivedMessage = student.getMessageLinkedList().getFirst();
        }

        assertNotNull(receivedMessage);
        assertEquals(messageContent, receivedMessage.getContentMessage());
        assertEquals(messageDate, receivedMessage.getPublicationDate());
        assertEquals(friend, receivedMessage.getUser());
    }
}