package introblaise.gui;

import java.io.IOException;

import introblaise.ui.IntroBlaise;
import introblaise.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for IntroBlaise using FXML.
 */
public class Main extends Application {

    private IntroBlaise introBlaise = new IntroBlaise();
    private Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Get the MainWindow controller
            MainWindow controller = fxmlLoader.getController();
            controller.setIntroBlaise(introBlaise); // Inject the IntroBlaise instance
            controller.showWelcomeMessage(ui); // Show the welcome message in the GUI

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
