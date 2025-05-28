package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DTO.ModeratorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModeratorDAOImpl implements ModeratorDAO {


        private final Connection connection;

        public ModeratorDAOImpl(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void save(ModeratorDTO user) {
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public ModeratorDTO findById(int id) {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new ModeratorDTO(rs.getString("name"), rs.getString("email"), rs.getString("id"), rs.getString("password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public List<ModeratorDTO> findAll() {
        return List.of();
    }

    @Override
    public void update(ModeratorDTO moderator) {

    }

    @Override
    public void delete(int id) {

    }

    }


