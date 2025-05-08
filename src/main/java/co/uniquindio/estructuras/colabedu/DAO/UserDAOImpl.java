package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.ModeratorDTO;
import co.uniquindio.estructuras.colabedu.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements  UserDAO{

    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(UserDTO user) {
        String sql = "INSERT INTO users (user_id, name, email,identifier,password  ) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getId());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(rs.getString("name"), rs.getString("email"), rs.getString("id"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(int id) {

    }
}
