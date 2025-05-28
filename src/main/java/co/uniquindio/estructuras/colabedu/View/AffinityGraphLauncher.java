package co.uniquindio.estructuras.colabedu.View;

import co.uniquindio.estructuras.colabedu.Controller.AffinityGraphController;
import co.uniquindio.estructuras.colabedu.Model.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AffinityGraphLauncher {

    public static void showAffinityGraph(Student student) {
        try {
            // 1. Cargar el FXML
            FXMLLoader loader = new FXMLLoader(
                    AffinityGraphLauncher.class.getResource("/co/uniquindio/estructuras/colabedu/AffinityGraphView.fxml"));

            Parent root = loader.load();

            // 2. Obtener el controlador
            AffinityGraphController controller = loader.getController();

            // 3. Configurar el escenario
            Stage stage = new Stage();
            stage.setTitle("Grafo de Afinidad - " + student.getName());
            stage.setScene(new Scene(root, 600, 400));

            // 4. Pasar los datos al controlador
            controller.setStudentData(student, stage);

            // 5. Mostrar la ventana
            stage.show();

        } catch (Exception e) {
            System.err.println("Error al cargar el grafo de afinidad:");
            e.printStackTrace();
        }
    }
}