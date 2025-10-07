package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Paper6_MiniFormValidation extends Application {
    @Override
    public void start(Stage stage) {
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");
        Button submitBtn = new Button("Submit");
        Label status = new Label();

        submitBtn.setOnAction(e -> {
            String email = emailField.getText();
            if (email.contains("@") && email.contains(".")) {
                status.setText("Valid");
                status.setTextFill(Color.GREEN);
            } else {
                status.setText("Invalid");
                status.setTextFill(Color.RED);
            }
        });

        VBox root = new VBox(15, emailField, submitBtn, status);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(root, 260, 140));
        stage.setTitle("Mini Form Validation");
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
