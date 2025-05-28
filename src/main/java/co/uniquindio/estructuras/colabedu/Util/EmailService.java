package co.uniquindio.estructuras.colabedu.Util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import java.io.File;

public class EmailService {

    public void sendPasswordRecoveryEmail(String email, String name, String password) {
        final String username = "noreply.colabedu@gmail.com";
        final String password_smtp = "vhlcigmhwlossyqa";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password_smtp);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.colabedu@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Recuperación de contraseña - ColabEdu");

            // Crear contenido HTML simple
            String htmlContent = "<p>Hola <strong>" + name + "</strong>,</p>"
                    + "<p>Has solicitado recuperar tu contraseña en ColabEdu.</p>"
                    + "<p>Tu contraseña es: <strong>" + password + "</strong></p>"
                    + "<p>Por favor, cambia tu contraseña después de iniciar sesión por motivos de seguridad.</p>"
                    + "<br>"
                    + "<p>Equipo ColabEdu</p>"
                    + "<p style='font-size:12px; color:gray;'>Este mensaje fue enviado automáticamente por el sistema de ColabEdu. Por favor, no respondas a este correo.</p>";

            // Establecer el contenido HTML directamente
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Correo de recuperación de contraseña enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al conectar con el servidor SMTP:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado:");
            e.printStackTrace();
        }
    }

    public void sendRegistrationEmail(String correo, String nombre) {
        final String username = "noreply.colabedu@gmail.com";
        final String password = "vhlcigmhwlossyqa";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.colabedu@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Confirmación de registro - ColabEdu");
            // Crear contenido HTML simple sin imagen
            String htmlContent = "<p>Hola <strong>" + nombre + "</strong>,</p>"
                    + "<p>Tu registro en ColabEdu fue todo un éxito. ¡Bienvenido a nuestra red para estudiantes!</p>"
                    + "<br>"
                    + "<p>Equipo ColabEdu</p>"
                    + "<p style='font-size:12px; color:gray;'>Este mensaje fue enviado automáticamente por el sistema de ColabEdu. Por favor, no respondas a este correo.</p>";

            // Establecer el contenido HTML directamente
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al conectar con el servidor SMTP:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado:");
            e.printStackTrace();
        }
    }
}