package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Test class for Content
 * This class tests the functionality of the Content class
 */
public class ContentTest {

    private Content content;
    private User user;
    private LocalDateTime publicationDate;
    private byte[] fileData;

    @BeforeEach
    public void setUp() {
        user = new Student("Test User", "test@example.com", "123456", "password");
        publicationDate = LocalDateTime.now();
        fileData = "Test file data".getBytes();
        content = new Content(
                "Test Content",
                publicationDate,
                "Document",
                "Test Description",
                "Computer Science",
                user,
                new Rating(),
                fileData,
                "test.txt",
                "text/plain"
        );
    }

    @Test
    @DisplayName("Test content creation with correct attributes")
    public void testContentCreation() {
        // Assert
        assertEquals("Test Content", content.getName());
        assertEquals(publicationDate, content.getPublicationDate());
        assertEquals("Document", content.getTypeContent());
        assertEquals("Test Description", content.getDescription());
        assertEquals("Computer Science", content.getSubject());
        assertEquals(user, content.getTheUser());
        assertArrayEquals(fileData, content.getFileData());
        assertEquals("test.txt", content.getFileName());
        assertEquals("text/plain", content.getFileType());
    }

    @Test
    @DisplayName("Test setting and getting content name")
    public void testSetAndGetName() {
        // Arrange
        String newName = "Updated Content Name";

        // Act
        content.setName(newName);

        // Assert
        assertEquals(newName, content.getName());
    }

    @Test
    @DisplayName("Test setting and getting content description")
    public void testSetAndGetDescription() {
        // Arrange
        String newDescription = "Updated description for testing purposes";

        // Act
        content.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, content.getDescription());
    }

    @Test
    @DisplayName("Test setting and getting content subject")
    public void testSetAndGetSubject() {
        // Arrange
        String newSubject = "Mathematics";

        // Act
        content.setSubject(newSubject);

        // Assert
        assertEquals(newSubject, content.getSubject());
    }

    @Test
    @DisplayName("Test getting file extension")
    public void testGetFileExtension() {
        // Assert
        assertEquals("txt", content.getFileExtension());

        // Test with different file name
        content.setFileName("document.pdf");
        assertEquals("pdf", content.getFileExtension());

        // Test with no extension
        content.setFileName("noextension");
        assertEquals("", content.getFileExtension());

        // Test with null filename
        content.setFileName(null);
        assertEquals("", content.getFileExtension());
    }

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        // Act
        String result = content.toString();

        // Assert
        assertTrue(result.contains("name='Test Content'"));
        assertTrue(result.contains("typeContent='Document'"));
        assertTrue(result.contains("description='Test Description'"));
        assertTrue(result.contains("subject='Computer Science'"));
        assertTrue(result.contains("fileName='test.txt'"));
        assertTrue(result.contains("fileType='text/plain'"));
        assertTrue(result.contains("fileDataSize=" + fileData.length + " bytes"));
    }
}