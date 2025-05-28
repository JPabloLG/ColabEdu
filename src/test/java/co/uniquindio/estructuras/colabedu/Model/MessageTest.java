package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Test class for Message
 * This class tests the functionality of the Message class
 */
public class MessageTest {

    private Message message;
    private User user;
    private LocalDateTime publicationDate;
    private String contentMessage;

    @BeforeEach
    public void setUp() {
        user = new Student("Test User", "test@example.com", "123456", "password");
        publicationDate = LocalDateTime.now();
        contentMessage = "Hello, this is a test message";
        message = new Message(publicationDate, contentMessage, user);
    }

    @Test
    @DisplayName("Test message creation with correct attributes")
    public void testMessageCreation() {
        // Assert
        assertEquals(contentMessage, message.getContentMessage());
        assertEquals(publicationDate, message.getPublicationDate());
        assertEquals(user, message.getUser());
    }

    @Test
    @DisplayName("Test setting and getting message content")
    public void testSetAndGetContentMessage() {
        // Arrange
        String newContent = "Updated message content";

        // Act
        message.setContentMessage(newContent);

        // Assert
        assertEquals(newContent, message.getContentMessage());
    }

    @Test
    @DisplayName("Test setting and getting publication date")
    public void testSetAndGetPublicationDate() {
        // Arrange
        LocalDateTime newDate = LocalDateTime.now().minusDays(1);

        // Act
        message.setPublicationDate(newDate);

        // Assert
        assertEquals(newDate, message.getPublicationDate());
    }

    @Test
    @DisplayName("Test setting and getting user")
    public void testSetAndGetUser() {
        // Arrange
        User newUser = new Student("New User", "new@example.com", "654321", "password");

        // Act
        message.setUser(newUser);

        // Assert
        assertEquals(newUser, message.getUser());
    }

    @Test
    @DisplayName("Test equals method with same message")
    public void testEqualsWithSameMessage() {
        // Arrange
        Message sameMessage = new Message(publicationDate, contentMessage, user);

        // Assert
        assertEquals(message, sameMessage);
        assertEquals(message.hashCode(), sameMessage.hashCode());
    }

    @Test
    @DisplayName("Test equals method with different message")
    public void testEqualsWithDifferentMessage() {
        // Arrange
        Message differentMessage = new Message(
                LocalDateTime.now().minusDays(1),
                "Different content",
                new Student("Different User", "diff@example.com", "987654", "password")
        );

        // Assert
        assertNotEquals(message, differentMessage);
        assertNotEquals(message.hashCode(), differentMessage.hashCode());
    }

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        // Act
        String result = message.toString();

        // Assert
        assertTrue(result.contains("contentMessage='" + contentMessage + "'"));
        assertTrue(result.contains("user=" + user.getName()));
        assertTrue(result.contains("publicationDate=" + publicationDate));
    }

    @Test
    @DisplayName("Test default constructor")
    public void testDefaultConstructor() {
        // Arrange
        Message defaultMessage = new Message();

        // Assert
        assertNull(defaultMessage.getContentMessage());
        assertNull(defaultMessage.getPublicationDate());
        assertNull(defaultMessage.getUser());
    }
}