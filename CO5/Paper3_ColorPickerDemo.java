package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Paper3_ColorPickerDemo extends Application {
    private final Color defaultColor = Color.LIGHTGRAY;

    @Override
    public void start(Stage stage) {
        ColorPicker picker = new ColorPicker(defaultColor);
        Pane pane = new Pane();
        pane.setPrefSize(300, 200);
        pane.setStyle("-fx-background-color: #D3D3D3;");
        Button resetBtn = new Button("Reset");

        picker.setOnAction(e -> {
            Color c = picker.getValue();
            pane.setStyle("-fx-background-color: " + toRgbString(c) + ";");
        });
        resetBtn.setOnAction(e -> {
            picker.setValue(defaultColor);
            pane.setStyle("-fx-background-color: #D3D3D3;");
        });

        VBox root = new VBox(15, picker, pane, resetBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(root));
        stage.setTitle("Color Picker Demo");
        stage.show();
    }

    private String toRgbString(Color c) {
        return String.format("rgb(%d,%d,%d)", (int)(c.getRed()*255), (int)(c.getGreen()*255), (int)(c.getBlue()*255));
    }

    // Update is implemented using a listener on ColorPicker; reset sets default color.
    public static void main(String[] args) { launch(args); }
}
