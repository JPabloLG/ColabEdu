package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;

public class Message {

    private String contentMessage;
    private User user;

    private LocalDateTime publicationDate;

    public Message(LocalDateTime publicationDate, String contentMessage, User user) {
        this.publicationDate = publicationDate;
        this.contentMessage = contentMessage;
        this.user = user;
    }

    public Message(String contentMessage, LocalDateTime publicationDate) {
        this.contentMessage = contentMessage;
        this.publicationDate = publicationDate;
    }

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

    public Message() {
    }
}
