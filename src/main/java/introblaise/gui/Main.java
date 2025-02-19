package introblaise.gui;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import introblaise.ui.IntroBlaise;
import introblaise.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            stage.setResizable(true);
            // Get the MainWindow controller
            MainWindow controller = fxmlLoader.getController();
            controller.setIntroBlaise(introBlaise); // Inject the IntroBlaise instance
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css"))
                    .toExternalForm());
            stage.setTitle("IntroBlaise");
            stage.getIcons().add(new Image(String.valueOf(Objects.requireNonNull(getClass()
                    .getResource("/images/IntroBlaisePfp.png")))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
