package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HelpRequest
 * This class tests the functionality of the HelpRequest class
 */
public class HelpRequestTest {

    private HelpRequest helpRequest;
    private String title;
    private String subject;
    private String description;
    private int priorityLevel;

    @BeforeEach
    public void setUp() {
        title = "Help with Data Structures";
        subject = "Computer Science";
        description = "I need help understanding binary trees";
        priorityLevel = 3;
        helpRequest = new HelpRequest(title, subject, description, priorityLevel);
    }

    @Test
    @DisplayName("Test help request creation with correct attributes")
    public void testHelpRequestCreation() {
        // Assert
        assertEquals(title, helpRequest.getTitle());
        assertEquals(subject, helpRequest.getSubject());
        assertEquals(description, helpRequest.getDescription());
        assertEquals(priorityLevel, helpRequest.getPriorityLevel());
    }

    @Test
    @DisplayName("Test setting and getting title")
    public void testSetAndGetTitle() {
        // Arrange
        String newTitle = "Help with Algorithms";

        // Act
        helpRequest.setTitle(newTitle);

        // Assert
        assertEquals(newTitle, helpRequest.getTitle());
    }

    @Test
    @DisplayName("Test setting and getting subject")
    public void testSetAndGetSubject() {
        // Arrange
        String newSubject = "Mathematics";

        // Act
        helpRequest.setSubject(newSubject);

        // Assert
        assertEquals(newSubject, helpRequest.getSubject());
    }

    @Test
    @DisplayName("Test setting and getting description")
    public void testSetAndGetDescription() {
        // Arrange
        String newDescription = "I need help with calculus problems";

        // Act
        helpRequest.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, helpRequest.getDescription());
    }

    @Test
    @DisplayName("Test setting and getting priority level")
    public void testSetAndGetPriorityLevel() {
        // Arrange
        int newPriorityLevel = 5;

        // Act
        helpRequest.setPriorityLevel(newPriorityLevel);

        // Assert
        assertEquals(newPriorityLevel, helpRequest.getPriorityLevel());
    }

    @Test
    @DisplayName("Test default constructor")
    public void testDefaultConstructor() {
        // Arrange
        HelpRequest defaultRequest = new HelpRequest();

        // Assert
        assertNull(defaultRequest.getTitle());
        assertNull(defaultRequest.getSubject());
        assertNull(defaultRequest.getDescription());
        assertEquals(0, defaultRequest.getPriorityLevel());
    }

    @Test
    @DisplayName("Test compareTo method with higher priority")
    public void testCompareToWithHigherPriority() {
        // Arrange
        HelpRequest higherPriority = new HelpRequest("Higher", "Subject", "Description", 5);

        // Act & Assert
        assertTrue(helpRequest.compareTo(higherPriority) < 0);
        assertTrue(higherPriority.compareTo(helpRequest) > 0);
    }

    @Test
    @DisplayName("Test compareTo method with equal priority")
    public void testCompareToWithEqualPriority() {
        // Arrange
        HelpRequest equalPriority = new HelpRequest("Equal", "Subject", "Description", 3);

        // Act & Assert
        assertEquals(0, helpRequest.compareTo(equalPriority));
        assertEquals(0, equalPriority.compareTo(helpRequest));
    }

    @Test
    @DisplayName("Test compareTo method with lower priority")
    public void testCompareToWithLowerPriority() {
        // Arrange
        HelpRequest lowerPriority = new HelpRequest("Lower", "Subject", "Description", 1);

        // Act & Assert
        assertTrue(helpRequest.compareTo(lowerPriority) > 0);
        assertTrue(lowerPriority.compareTo(helpRequest) < 0);
    }

    @Test
    @DisplayName("Test student creating and getting help request")
    public void testStudentCreatingAndGettingHelpRequest() {
        // Arrange
        Student student = new Student("Test Student", "test@example.com", "123456", "password");

        // Act
        student.createHelpRequest(title, subject, description, priorityLevel);
        HelpRequest retrievedRequest = student.getNextHelpRequest();

        // Assert
        assertNotNull(retrievedRequest);
        assertEquals(title, retrievedRequest.getTitle());
        assertEquals(subject, retrievedRequest.getSubject());
        assertEquals(description, retrievedRequest.getDescription());
        assertEquals(priorityLevel, retrievedRequest.getPriorityLevel());
    }
}