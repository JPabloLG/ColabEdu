package co.uniquindio.estructuras.colabedu.Util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {
    public void enviarCorreo(String correo, String nombre) {
        // Tu correo y contraseña de aplicación (no la contraseña normal)
        final String username = "noreply.colabedu@gmail.com";
        final String password = "vhlcigmhwlossyqa";

        // Configuración de propiedades SMTP para Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Crear sesión autenticada
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        try {
            // Crear un mensaje simple
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.colabedu@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
            message.setSubject("Confirmación de registro - ColabEdu");
            message.setText("Hola " + nombre + ", tu resgitro en ColabEdu fue todo un éxito");

            // Enviar mensaje
            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al conectar con el servidor SMTP:");
            e.printStackTrace();
        }
    }
}