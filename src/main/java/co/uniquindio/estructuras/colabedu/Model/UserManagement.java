package co.uniquindio.estructuras.colabedu.Model;

public interface UserManagement {

    public void deleteUser(User user);

    public void updateUser(User user);

    public void createUser(String name, String id, String email, String password);
}
