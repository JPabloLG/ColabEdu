package co.uniquindio.estructuras.colabedu.DAO;

import java.util.List;

public interface ContentDAO {
    void save(ContentDTO content);
    ContentDTO findById(int id);
    List<ContentDTO> findAll();
    List<ContentDTO> findByUserId(String userId);
    void update(ContentDTO user);
    void delete(int id);
}
