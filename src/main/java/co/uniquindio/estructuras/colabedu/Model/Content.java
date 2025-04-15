package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;

public class Content {

    private String name;
    private LocalDateTime publicationDate;
    private String typeContent;
    private String description;
    private String subject;
    public User theUser;
    public Rating theRating;

    public Content(String name, LocalDateTime publicationDate, String typeContent, String description, String subject, User theUser, Rating theRating) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.typeContent = typeContent;
        this.description = description;
        this.subject = subject;
        this.theUser = theUser;
        this.theRating = theRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }

    public Rating getTheRating() {
        return theRating;
    }

    public void setTheRating(Rating theRating) {
        this.theRating = theRating;
    }

    @Override
    public String toString() {
        return "Content{" +
                "name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", typeContent='" + typeContent + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", theUser=" + theUser +
                ", theRating=" + theRating +
                '}';
    }
}
