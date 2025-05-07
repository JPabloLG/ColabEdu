package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.ModeratorDTO;
import co.uniquindio.estructuras.colabedu.DTO.UserDTO;

import java.util.List;

public interface UserDAO {
    void save(UserDTO user);
    UserDTO findById(int id);
    List<UserDTO> findAll();
    void update(UserDTO user);
    void delete(int id);
}
