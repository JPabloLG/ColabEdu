package co.uniquindio.estructuras.colabedu.DTO;

import java.time.LocalDate;

public class PostDTO {
    private int id;
    private String title;
    private String description;
    private int authorId;
    private LocalDate dateCreated;

    public PostDTO(String title, int authorId, LocalDate dateCreated, int id, String summary) {
        this.title = title;
        this.authorId = authorId;
        this.dateCreated = dateCreated;
        this.id = id;
        this.description = summary;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "authorId=" + authorId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}