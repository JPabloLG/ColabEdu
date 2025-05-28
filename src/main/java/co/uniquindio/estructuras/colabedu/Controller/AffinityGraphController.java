package co.uniquindio.estructuras.colabedu.Controller;

import co.uniquindio.estructuras.colabedu.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class AffinityGraphController {

    @FXML private Pane graphPane;
    private Student currentStudent;
    private Stage currentStage;

    @FXML
    public void initialize() {
        System.out.println("Inicializando controlador del grafo...");
        graphPane.setStyle("-fx-background-color: #f5f5f5;");
    }

    public void setStudentData(Student student, Stage stage) {
        this.currentStudent = student;
        this.currentStage = stage;
        drawGraph();
    }

    private void drawGraph() {
        graphPane.getChildren().clear();

        if (currentStudent == null) {
            return;
        }

        // Obtener dimensiones del panel
        double centerX = graphPane.getWidth() / 2;
        double centerY = graphPane.getHeight() / 2;

        // Si no tenemos dimensiones, usar valores por defecto
        if (centerX == 0) centerX = 300;
        if (centerY == 0) centerY = 250;

        // Nodo central
        Circle centerNode = new Circle(centerX, centerY, 30);
        centerNode.setFill(Color.LIGHTBLUE);
        centerNode.setStroke(Color.DARKBLUE);
        centerNode.setStrokeWidth(2);

        Text centerText = new Text(centerX - 25, centerY + 40, currentStudent.getName());
        centerText.setStyle("-fx-font-weight: bold;");

        graphPane.getChildren().addAll(centerNode, centerText);

        // Obtener amigos
        List<Student> friends = currentStudent.getFriends();
        if (friends.isEmpty()) {
            Text noFriends = new Text(centerX - 100, centerY - 50, "No hay conexiones para mostrar");
            noFriends.setStyle("-fx-font-size: 16; -fx-fill: #666;");
            graphPane.getChildren().add(noFriends);
            return;
        }

        // Configuración del círculo de amigos
        double radius = Math.min(centerX, centerY) * 0.7; // Radio del círculo de amigos
        double angleStep = 2 * Math.PI / friends.size();
        double currentAngle = 0;

        // Dibujar amigos en círculo
        for (Student friend : friends) {
            // Calcular posición del amigo
            double friendX = centerX + radius * Math.cos(currentAngle);
            double friendY = centerY + radius * Math.sin(currentAngle);

            // Línea de conexión (diagonal)
            Line connection = new Line(centerX, centerY, friendX, friendY);
            connection.setStroke(Color.GRAY);
            connection.setStrokeWidth(2);

            // Nodo amigo
            Circle friendNode = new Circle(friendX, friendY, 20);
            friendNode.setFill(Color.LIGHTCORAL);
            friendNode.setStroke(Color.DARKRED);
            friendNode.setStrokeWidth(2);

            // Nombre del amigo
            Text friendText = new Text(friendX - 20, friendY + 30, friend.getName());

            // Hacer nodos interactivos
            friendNode.setOnMouseClicked(e -> openFriendGraph(friend));

            // Añadir elementos al panel
            graphPane.getChildren().addAll(connection, friendNode, friendText);

            currentAngle += angleStep;
        }
    }

    private void openFriendGraph(Student friend) {
        // Implementación para abrir grafo del amigo
    }

    @FXML
    private void handleRefresh() {
        drawGraph();
    }

    @FXML
    private void handleClose() {
        if (currentStage != null) {
            currentStage.close();
        }
    }
}