package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Paper5_SimpleTextFormatter extends Application {
    @Override
    public void start(Stage stage) {
        Label sample = new Label("Sample Text");
        ChoiceBox<String> styleBox = new ChoiceBox<>();
        styleBox.getItems().addAll("Normal", "Bold", "Italic", "Bold Italic");
        styleBox.setValue("Normal");
        Slider sizeSlider = new Slider(10, 36, 16);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);

        styleBox.setOnAction(e -> updateFont(sample, styleBox, sizeSlider));
        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateFont(sample, styleBox, sizeSlider));

        VBox root = new VBox(15, sample, styleBox, sizeSlider);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(root, 300, 180));
        stage.setTitle("Text Formatter");
        stage.show();
    }

    private void updateFont(Label label, ChoiceBox<String> styleBox, Slider sizeSlider) {
        String style = styleBox.getValue();
        double size = sizeSlider.getValue();
        FontWeight weight = FontWeight.NORMAL;
        FontPosture posture = FontPosture.REGULAR;
        if ("Bold".equals(style)) weight = FontWeight.BOLD;
        if ("Italic".equals(style)) posture = FontPosture.ITALIC;
        if ("Bold Italic".equals(style)) {
            weight = FontWeight.BOLD;
            posture = FontPosture.ITALIC;
        }
        label.setFont(Font.font("System", weight, posture, size));
    }

    // Font is applied using Font.font(...) in a listener for style and size changes.
    public static void main(String[] args) { launch(args); }
}
