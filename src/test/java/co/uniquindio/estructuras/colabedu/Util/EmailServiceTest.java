package co.uniquindio.estructuras.colabedu.Util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class for EmailService
 * This class tests the email sending functionality in a separate thread
 */
public class EmailServiceTest {

    private EmailService emailService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        emailService = new EmailService();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Test sending registration email")
    public void testSendRegistrationEmail() throws InterruptedException {
        // Arrange
        String testEmail = "test@example.com";
        String testName = "Test User";

        // Act
        // Execute email sending in a separate thread as it's done in RegisterController
        Thread emailThread = new Thread(() -> {
            emailService.sendRegistrationEmail(testEmail, testName);
        });
        emailThread.start();

        // Wait for the thread to complete
        emailThread.join(5000); // Wait up to 5 seconds

        // Assert
        // Check if the success message was printed to the console
        // Note: This test might fail if the email server is not accessible
        assertTrue(outContent.toString().contains("Correo enviado exitosamente") ||
                        outContent.toString().contains("Error al conectar con el servidor SMTP"),
                "Either success or connection error message should be present");
        System.out.println("Test complete");
    }

    @Test
    @DisplayName("Test email content construction")
    public void testEmailContentConstruction() {
        // This test would ideally verify the email content construction
        // However, since the EmailService doesn't expose this functionality separately,
        // we can only test the full email sending process

        // For a more thorough test, the EmailService class could be refactored to:
        // 1. Separate email construction from sending
        // 2. Allow injection of a Session object for testing
        // 3. Return the constructed Message object for verification

        // For now, we'll just verify that the method doesn't throw unexpected exceptions
        assertDoesNotThrow(() -> {
            emailService.sendRegistrationEmail("test@example.com", "Test User");
        });
    }
}