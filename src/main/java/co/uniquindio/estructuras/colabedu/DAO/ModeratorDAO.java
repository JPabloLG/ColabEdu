package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.ModeratorDTO;

import java.util.List;

public interface ModeratorDAO {
    void save(ModeratorDTO moderator);
    ModeratorDTO findById(int id);
    List<ModeratorDTO> findAll();
    void update(ModeratorDTO moderator);
    void delete(int id);
}
