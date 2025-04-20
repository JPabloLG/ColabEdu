package co.uniquindio.estructuras.colabedu.DTO;

public class UserDTO {
    private String name;
    private String email;
    private String id;

    // Constructor con todos los campos
    public UserDTO(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
