package roberto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * public class Main that sets the stage of the application
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        assert stage != null : "stage must not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            Roberto roberto = new Roberto("taskList.txt", fxmlLoader.<MainWindow>getController());
            fxmlLoader.<MainWindow>getController().setRoberto(roberto); // inject the Duke instance
            roberto.robertoGreet();
            stage.show();
            stage.setTitle("Roberto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
