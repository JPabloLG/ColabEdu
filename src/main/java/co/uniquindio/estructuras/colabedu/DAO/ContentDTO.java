package co.uniquindio.estructuras.colabedu.DAO;

import java.time.LocalDateTime;

public class ContentDTO {
    private int id;
    private String title;
    private String description;
    private String contentType;
    private String contentUrl;
    private int userId;
    private LocalDateTime publicationDate;
    private String subject;

    public ContentDTO(int id, String title, String description, String contentType, String contentUrl, int userId, LocalDateTime publicationDate, String subject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentType = contentType;
        this.contentUrl = contentUrl;
        this.userId = userId;
        this.publicationDate = publicationDate;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
