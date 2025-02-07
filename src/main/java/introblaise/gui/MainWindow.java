package introblaise.gui;

import introblaise.ui.IntroBlaise;
import introblaise.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Button sendButton;
    @FXML
    private Label welcomeLabel; // Label for welcome message

    private IntroBlaise introBlaise;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the IntroBlaise instance */
    public void setIntroBlaise(IntroBlaise intro) {
        introBlaise = intro;
    }

    /**
     * Displays the welcome message from the Ui object in the welcome label.
     *
     * This method calls the {@link Ui#showWelcome()} method to retrieve the welcome message
     * and then sets it as the text of the welcome label in the GUI.
     *
     * @param ui The ui instance that generated the welcome message to be displayed.
     */
    public void showWelcomeMessage(Ui ui) {
        welcomeLabel.setText(ui.showWelcome());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = introBlaise.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}

