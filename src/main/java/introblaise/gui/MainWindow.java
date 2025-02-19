package introblaise.gui;

import java.util.Objects;

import introblaise.ui.IntroBlaise;
import introblaise.ui.Ui;
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
    private IntroBlaise introBlaise;
    private Ui ui = new Ui();

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/UserPfp.png")));
    private Image introBlaiseImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/IntroBlaisePfp.png")));

    /**
     * Initializes scroll pane and loads CSS file.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = ui.showWelcome();
        DialogBox welcomeDialogBox = DialogBox.getIntroBlaiseDialog(welcomeMessage, introBlaiseImage);
        dialogContainer.getChildren().add(welcomeDialogBox);

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Load CSS file
        String cssUrl = Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm();
        getStylesheets().add(cssUrl);
    }

    /** Injects the IntroBlaise instance */
    public void setIntroBlaise(IntroBlaise intro) {
        introBlaise = intro;
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
                DialogBox.getIntroBlaiseDialog(response, introBlaiseImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}

