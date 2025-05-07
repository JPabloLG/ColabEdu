package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.UserDTO;

import java.util.List;

public interface ContentDAO {
    void save(ContentDTO content);
    ContentDTO findById(int id);
    List<ContentDTO> findAll();
    void update(ContentDTO user);
    void delete(int id);
}
