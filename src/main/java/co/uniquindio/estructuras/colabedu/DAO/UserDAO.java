package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.StudentDTO;

import java.util.List;

public interface UserDAO {
    void save(StudentDTO user);
    StudentDTO findById(int id);
    List<StudentDTO> findAll();
    void update(StudentDTO user);
    void delete(int id);
}
