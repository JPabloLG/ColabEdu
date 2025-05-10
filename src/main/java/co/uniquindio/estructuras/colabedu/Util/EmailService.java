package co.uniquindio.estructuras.colabedu.Util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import java.io.File;

public class EmailService {

    public void enviarCorreo(String correo, String nombre) {
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

            // 1. Crear parte HTML
            String htmlContent = "<p>Hola <strong>" + nombre + "</strong>,</p>"
                    + "<p>Tu registro en ColabEdu fue todo un éxito. ¡Bienvenido a nuestra red para estudiantes!</p>"
                    + "<br>"
                    + "<img src='cid:signature' alt='Firma ColabEdu' style='width:400px;'>"
                    + "<p style='font-size:12px; color:gray;'>Este mensaje fue enviado automáticamente por el sistema de ColabEdu. Por favor, no respondas a este correo.</p>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            // 2. Crear parte de imagen
            MimeBodyPart imagePart = new MimeBodyPart();

            // Usando rutas relativas desde resources
            String imagePath = "images/signatureEmail.png";
            File imageFile = new File(getClass().getClassLoader().getResource(imagePath).getFile());

            // Configurar DataSource correctamente
            FileDataSource fds = new FileDataSource(imageFile);
            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.setHeader("Content-ID", "<signature>");
            imagePart.setDisposition(MimeBodyPart.INLINE);

            // 3. Combinar partes
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(imagePart);

            message.setContent(multipart);

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