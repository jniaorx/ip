package introblaise.gui;

import java.io.IOException;

import introblaise.ui.IntroBlaise;
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

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIntroBlaise(introBlaise);  // inject the IntroBlaise instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
