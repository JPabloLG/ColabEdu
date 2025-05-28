package co.uniquindio.estructuras.colabedu.DTO;

public class FileDTO {
    private int id;
    private String name;
    private String url;
    private int uploadedById;

    public FileDTO(int id, String url, int uploadedById, String name) {
        this.id = id;
        this.url = url;
        this.uploadedById = uploadedById;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUploadedById() {
        return uploadedById;
    }

    public void setUploadedById(int uploadedById) {
        this.uploadedById = uploadedById;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", uploadedById=" + uploadedById +
                '}';
    }
}