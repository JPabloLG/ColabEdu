package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.DB.JDBC;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ContentDAOImpl implements ContentDAO{

    private Connection connection;

    public ContentDAOImpl() {
        this.connection = JDBC.getConnection();
        createTableIfNotExists();
    }

    /**
     * Crea la tabla de contenido en la base de datos si no existe.
     */
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS contents (" +
                     "content_id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "user_id INT NOT NULL, " +
                     "name VARCHAR(255) NOT NULL, " +
                     "publication_date TIMESTAMP NOT NULL, " +
                     "type_content VARCHAR(50) NOT NULL, " +
                     "description TEXT, " +
                     "subject VARCHAR(100), " +
                     "file VARCHAR(255)" +
                     ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'content' creada o ya existente");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla 'content': " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void save(ContentDTO content) {
        String sql = "INSERT INTO contents (user_id, name, publication_date, type_content, description, subject, file) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, content.getUserId());
            stmt.setString(2, content.getTitle());
            stmt.setTimestamp(3, Timestamp.valueOf(content.getPublicationDate()));
            stmt.setString(4, content.getContentType());
            stmt.setString(5, content.getDescription());
            stmt.setString(6, content.getSubject());
            stmt.setString(7, content.getContentUrl());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating content failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    content.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating content failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving content: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ContentDTO findById(int id) {
        return null;
    }

    @Override
    public List<ContentDTO> findAll() {
        return List.of();
    }

    @Override
    public List<ContentDTO> findByUserId(String userId) {
        List<ContentDTO> contents = new ArrayList<>();
        String sql = "SELECT * FROM contents WHERE user_id = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ContentDTO content = new ContentDTO(
                    rs.getInt("content_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("type_content"),
                    rs.getString("file"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("publication_date").toLocalDateTime(),
                    rs.getString("subject")
                );
                contents.add(content);
            }
        } catch (SQLException e) {
            System.err.println("Error finding contents by user ID: " + e.getMessage());
            e.printStackTrace();
        }

        return contents;
    }

    @Override
    public void update(ContentDTO user) {

    }

    @Override
    public void delete(int id) {

    }
}
