package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private String contentMessage;
    private User user; // Asumimos que User es la clase padre de Student
    private LocalDateTime publicationDate;

    public Message() {
    }

    public Message(String contentMessage, LocalDateTime publicationDate) {
        this.contentMessage = contentMessage;
        this.publicationDate = publicationDate;
    }

    public Message(LocalDateTime publicationDate, String contentMessage, User user) {
        this.publicationDate = publicationDate;
        this.contentMessage = contentMessage;
        this.user = user;
    }

    // Getters y Setters
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // equals y hashCode para mejor manejo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(contentMessage, message.contentMessage) &&
                Objects.equals(user, message.user) &&
                Objects.equals(publicationDate, message.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentMessage, user, publicationDate);
    }

    @Override
    public String toString() {
        return "Message{" +
                "contentMessage='" + contentMessage + '\'' +
                ", user=" + (user != null ? user.getName() : "null") +
                ", publicationDate=" + publicationDate +
                '}';
    }
}