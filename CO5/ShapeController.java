package co5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ShapeController {
    private final ShapeModel model;
    private final ShapeView view;

    public ShapeController(ShapeModel m, ShapeView v) {
        this.model = m;
        this.view = v;

        // Bind label text to shape count
        view.countLabel.textProperty().set("Shapes: 0");

        view.drawBtn.setOnAction(e -> {
            String type = view.shapeBox.getValue();
            Color color = view.colorPicker.getValue();
            if ("Circle".equals(type)) {
                Circle c = new Circle(40, color);
                c.setCenterX(60 + Math.random() * 180);
                c.setCenterY(60 + Math.random() * 80);
                view.drawPane.getChildren().add(c);
            } else {
                Rectangle r = new Rectangle(80, 40, color);
                r.setX(60 + Math.random() * 120);
                r.setY(60 + Math.random() * 80);
                view.drawPane.getChildren().add(r);
            }
            model.incrementCount();
            view.countLabel.setText("Shapes: " + view.drawPane.getChildren().size());
        });

        view.clearBtn.setOnAction(e -> {
            view.drawPane.getChildren().clear();
            model.resetCount();
            view.countLabel.setText("Shapes: 0");
        });
    }
}
