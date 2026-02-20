package roberto;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Roberto roberto;

    private final Image userImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/incredible.png")));
    private final Image dukeImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/freddy.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRoberto(Roberto r) {
        assert r != null : "roberto should not be null";
        roberto = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        roberto.getResponse(input, false);
        userInput.clear();
    }

    public void exit() {
        Platform.exit();
    }

    /**
     * Adds a new dialog as response from Roberto
     * @param output String Roberto replies with
     */
    public void addRobertoDialog(String output) {
        dialogContainer.getChildren().addAll(
                DialogBox.getRobertoDialog(output, dukeImage)
        );
    }
}
