package co.uniquindio.estructuras.colabedu.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for LinkedList
 * This class tests the functionality of the custom LinkedList implementation
 */
public class LinkedListTest {

    private LinkedList<String> linkedList;

    @BeforeEach
    public void setUp() {
        linkedList = new LinkedList<>();
    }

    @Test
    @DisplayName("Test initial state of linked list")
    public void testInitialState() {
        // Assert
        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.getSize());
        assertNull(linkedList.getHead());
    }

    @Test
    @DisplayName("Test adding elements to linked list")
    public void testAddElements() {
        // Act
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");

        // Assert
        assertFalse(linkedList.isEmpty());
        assertEquals(3, linkedList.getSize());
        assertNotNull(linkedList.getHead());
        assertEquals("Third", linkedList.getHead().getDate());
    }

    @Test
    @DisplayName("Test deleting elements from linked list")
    public void testDeleteElements() {
        // Arrange
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        assertEquals(3, linkedList.getSize());

        // Act
        linkedList.delete("Second");

        // Assert
        assertEquals(2, linkedList.getSize());
        assertTrue(linkedList.searchElement("First"));
        assertFalse(linkedList.searchElement("Second"));
        assertTrue(linkedList.searchElement("Third"));
    }

    @Test
    @DisplayName("Test deleting head element from linked list")
    public void testDeleteHeadElement() {
        // Arrange
        linkedList.add("First");
        linkedList.add("Second");
        assertEquals(2, linkedList.getSize());
        assertEquals("Second", linkedList.getHead().getDate());

        // Act
        linkedList.delete("Second");

        // Assert
        assertEquals(1, linkedList.getSize());
        assertEquals("First", linkedList.getHead().getDate());
    }

    @Test
    @DisplayName("Test deleting from empty list throws exception")
    public void testDeleteFromEmptyList() {
        // Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            linkedList.delete("NonExistent");
        });

        assertEquals("Cannot delete from an empty list", exception.getMessage());
    }

    @Test
    @DisplayName("Test searching for elements in linked list")
    public void testSearchElement() {
        // Arrange
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");

        // Assert
        assertTrue(linkedList.searchElement("First"));
        assertTrue(linkedList.searchElement("Second"));
        assertTrue(linkedList.searchElement("Third"));
        assertFalse(linkedList.searchElement("Fourth"));
    }

    @Test
    @DisplayName("Test searching in empty list returns false")
    public void testSearchInEmptyList() {
        // Assert
        assertFalse(linkedList.searchElement("Any"));
    }

    @Test
    @DisplayName("Test setting and getting head node")
    public void testSetAndGetHead() {
        // Arrange
        Node<String> newNode = new Node<>("Test");

        // Act
        linkedList.setHead(newNode);

        // Assert
        assertSame(newNode, linkedList.getHead());
        assertEquals("Test", linkedList.getHead().getDate());
    }

    @Test
    @DisplayName("Test setting and getting size")
    public void testSetAndGetSize() {
        // Act
        linkedList.setSize(10);

        // Assert
        assertEquals(10, linkedList.getSize());
    }
}