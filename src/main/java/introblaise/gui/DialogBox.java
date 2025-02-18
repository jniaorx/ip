package introblaise.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(introblaise.gui.MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set text and image
        dialog.setText(text);
        displayPicture.setImage(img);

        makeCircular(displayPicture);

        // Add CSS classes
        this.getStyleClass().add("dialog-box"); // Style for the entire HBox
        dialog.getStyleClass().add("chat-label"); // Style for the label (chat bubble)
        displayPicture.getStyleClass().add("image-view"); // Style for the profile picture
    }

    private void makeCircular(ImageView imageView) {
        Circle clip = new Circle();
        clip.centerXProperty().bind(imageView.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(imageView.fitHeightProperty().divide(2));
        clip.radiusProperty().bind(Bindings.min(imageView.fitWidthProperty(), imageView.fitHeightProperty()).divide(2));
        imageView.setClip(clip);
    }
    /**
     * Sets the style for the dialog box (user or bot).
     */
    public void setDialogStyle(String styleClass) {
        dialog.getStyleClass().add(styleClass); // Apply the specific style (user or bot)
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDialogStyle("user-dialog"); // Apply user-dialog style
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDialogStyle("bot-dialog"); // Apply bot-dialog style
        db.flip();
        return db;
    }
}
