package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StudyGroup
 * This class tests the functionality of the StudyGroup class
 */
public class StudyGroupTest {

    private StudyGroup studyGroup;
    private String groupName;
    private String description;

    @BeforeEach
    public void setUp() {
        groupName = "Test Study Group";
        description = "This is a test study group for computer science students";
        studyGroup = new StudyGroup(groupName, description);
    }

    @Test
    @DisplayName("Test study group creation with correct attributes")
    public void testStudyGroupCreation() {
        // Assert
        assertEquals(groupName, studyGroup.getNameGroup());
        assertEquals(description, studyGroup.getDescription());
    }

    @Test
    @DisplayName("Test setting and getting group name")
    public void testSetAndGetNameGroup() {
        // Arrange
        String newName = "Updated Study Group Name";

        // Act
        studyGroup.setNameGroup(newName);

        // Assert
        assertEquals(newName, studyGroup.getNameGroup());
    }

    @Test
    @DisplayName("Test setting and getting description")
    public void testSetAndGetDescription() {
        // Arrange
        String newDescription = "Updated description for the study group";

        // Act
        studyGroup.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, studyGroup.getDescription());
    }

    @Test
    @DisplayName("Test default constructor")
    public void testDefaultConstructor() {
        // Arrange
        StudyGroup defaultGroup = new StudyGroup();

        // Assert
        assertNull(defaultGroup.getNameGroup());
        assertNull(defaultGroup.getDescription());
    }

    @Test
    @DisplayName("Test student joining and leaving study group")
    public void testStudentJoiningAndLeavingGroup() {
        // Arrange
        Student student = new Student("Test Student", "test@example.com", "123456", "password");

        // Act - Student joins the group
        student.joinStudyGroup(studyGroup);

        // Assert
        assertTrue(student.getStudyGroupLinkedList().contains(studyGroup));

        // Act - Student leaves the group
        student.leaveStudyGroup(studyGroup);

        // Assert
        assertFalse(student.getStudyGroupLinkedList().contains(studyGroup));
    }

    @Test
    @DisplayName("Test multiple students in same study group")
    public void testMultipleStudentsInSameGroup() {
        // Arrange
        Student student1 = new Student("Student 1", "student1@example.com", "111111", "password");
        Student student2 = new Student("Student 2", "student2@example.com", "222222", "password");

        // Act
        student1.joinStudyGroup(studyGroup);
        student2.joinStudyGroup(studyGroup);

        // Assert
        assertTrue(student1.getStudyGroupLinkedList().contains(studyGroup));
        assertTrue(student2.getStudyGroupLinkedList().contains(studyGroup));

        // Verify that both students have the same study group object
        StudyGroup student1Group = student1.getStudyGroupLinkedList().getFirst();
        StudyGroup student2Group = student2.getStudyGroupLinkedList().getFirst();

        assertSame(student1Group, student2Group);
    }
}