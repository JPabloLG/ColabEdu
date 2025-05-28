package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.DAO.ContentDAO;
import co.uniquindio.estructuras.colabedu.DAO.ContentDAOImpl;
import co.uniquindio.estructuras.colabedu.DAO.ContentDTO;
import co.uniquindio.estructuras.colabedu.Model.AcademicSocialNetwork;
import co.uniquindio.estructuras.colabedu.Model.Content;
import co.uniquindio.estructuras.colabedu.Model.Student;
import co.uniquindio.estructuras.colabedu.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

public class ContentsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox contenedorContenidos;

    @FXML
    private ScrollPane content_container;

    @FXML
    private TextField txt_search;

    private ContentDAO contentDAO;

    public ContentsController() {
        this.contentDAO = new ContentDAOImpl(co.uniquindio.estructuras.colabedu.DB.JDBC.getConnection());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarContenidos();
    }

    /**
     * Convierte un ContentDTO a un objeto Content
     * @param dto El objeto ContentDTO a convertir
     * @return Un objeto Content con los datos del DTO
     */
    private Content convertDTOToContent(ContentDTO dto) {
        // Obtener el usuario actual
        User currentUser = AcademicSocialNetwork.getSingleton().getCurrentUser();

        // Crear un objeto Content con los datos del DTO
        Content content = new Content(
                dto.getTitle(),
                dto.getPublicationDate(),
                dto.getContentType(),
                dto.getDescription(),
                dto.getSubject(),
                currentUser,
                null, // No tenemos rating en el DTO
                new byte[0], // No tenemos fileData en el DTO
                dto.getContentUrl(), // Usamos contentUrl como fileName
                dto.getContentType() // Usamos contentType como fileType
        );

        return content;
    }

    private void cargarContenidos() {
        contenedorContenidos.getChildren().clear();

        // Obtener el usuario actual
        User currentUser = AcademicSocialNetwork.getSingleton().getCurrentUser();
        if (currentUser == null) {
            System.err.println("No hay usuario actual");
            return;
        }

        // Obtener los contenidos del usuario desde la base de datos
        List<ContentDTO> contentDTOs = contentDAO.findByUserId(currentUser.getId());
        List<Content> contents = new ArrayList<>();

        // Convertir los DTOs a objetos Content
        for (ContentDTO dto : contentDTOs) {
            contents.add(convertDTOToContent(dto));
        }

        // Mostrar los contenidos
        for (Content contenido : contents) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                AnchorPane card = loader.load();

                ContentCardController cardController = loader.getController();
                cardController.inicializarDatos(contenido);

                card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                VBox.setMargin(card, new Insets(0, 0, 15, 0));

                contenedorContenidos.getChildren().add(card);

            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de contenido: " + e.getMessage());
            }
        }

        // Si no hay contenidos, mostrar también los contenidos temporales
        if (contents.isEmpty()) {
            for (Content contenido : PrincipalController.contenidosTemporales) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                    AnchorPane card = loader.load();

                    ContentCardController cardController = loader.getController();
                    cardController.inicializarDatos(contenido);

                    card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                    VBox.setMargin(card, new Insets(0, 0, 15, 0));

                    contenedorContenidos.getChildren().add(card);

                } catch (IOException e) {
                    System.err.println("Error al cargar la tarjeta de contenido: " + e.getMessage());
                }
            }
        }
    }

    @FXML
    void btn_colabEdu(MouseEvent event) throws IOException {
        App.setRoot("PrincipalView", "ColabEdu - Página principal");
    }

    @FXML
    void btnSearch(MouseEvent event) {
        contenedorContenidos.getChildren().clear();

        // Obtener el usuario actual
        User currentUser = AcademicSocialNetwork.getSingleton().getCurrentUser();
        if (currentUser == null) {
            System.err.println("No hay usuario actual");
            return;
        }

        // Obtener el texto de búsqueda
        String searchText = txt_search.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            // Si no hay texto de búsqueda, cargar todos los contenidos
            cargarContenidos();
            return;
        }

        // Obtener los contenidos del usuario desde la base de datos
        List<ContentDTO> contentDTOs = contentDAO.findByUserId(currentUser.getId());
        List<Content> filteredContents = new ArrayList<>();

        // Convertir los DTOs a objetos Content y filtrar por el texto de búsqueda
        for (ContentDTO dto : contentDTOs) {
            // Verificar si el título, descripción o materia contiene el texto de búsqueda
            if (dto.getTitle().toLowerCase().contains(searchText) ||
                    (dto.getDescription() != null && dto.getDescription().toLowerCase().contains(searchText)) ||
                    (dto.getSubject() != null && dto.getSubject().toLowerCase().contains(searchText))) {

                filteredContents.add(convertDTOToContent(dto));
            }
        }

        // Mostrar los contenidos filtrados
        for (Content contenido : filteredContents) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                AnchorPane card = loader.load();

                ContentCardController cardController = loader.getController();
                cardController.inicializarDatos(contenido);

                card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                VBox.setMargin(card, new Insets(0, 0, 15, 0));

                contenedorContenidos.getChildren().add(card);

            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de contenido: " + e.getMessage());
            }
        }

        // Si no hay contenidos filtrados, mostrar un mensaje
        if (filteredContents.isEmpty()) {
            System.out.println("No se encontraron contenidos que coincidan con la búsqueda: " + searchText);
        }
    }

    @FXML
    void initialize() {
        assert contenedorContenidos != null : "fx:id=\"contenedorContenidos\" was not injected: check your FXML file 'ContentsView.fxml'.";
        assert content_container != null : "fx:id=\"content_container\" was not injected: check your FXML file 'ContentsView.fxml'.";
        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'ContentsView.fxml'.";
    }
}