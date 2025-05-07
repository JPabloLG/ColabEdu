package co.uniquindio.estructuras.colabedu.DAO;

public class ContentDTO {
    private int id;
    private String title;
    private String description;
    private String contentType;
    private String contentUrl;
    private int userId;

    public ContentDTO(int id, String title, String description, String contentType, String contentUrl, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentType = contentType;
        this.contentUrl = contentUrl;
        this.userId = userId;
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
}
