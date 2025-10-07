package co5;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ShapeModel {
    private final ObjectProperty<String> shapeType = new SimpleObjectProperty<>("Circle");
    private int shapeCount = 0;

    public String getShapeType() { return shapeType.get(); }
    public void setShapeType(String s) { shapeType.set(s); }
    public ObjectProperty<String> shapeTypeProperty() { return shapeType; }

    public int getShapeCount() { return shapeCount; }
    public void setShapeCount(int count) { shapeCount = count; }
    public void incrementCount() { shapeCount++; }
    public void resetCount() { shapeCount = 0; }
}
