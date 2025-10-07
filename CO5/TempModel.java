package co5;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TempModel {
    public final DoubleProperty celsius = new SimpleDoubleProperty(0);
    public final DoubleProperty fahrenheit = new SimpleDoubleProperty(32);
    public final DoubleProperty kelvin = new SimpleDoubleProperty(273.15);

    public TempModel() {
        celsius.addListener((obs, old, val) -> {
            fahrenheit.set(val.doubleValue() * 9/5 + 32);
            kelvin.set(val.doubleValue() + 273.15);
        });
        fahrenheit.addListener((obs, old, val) -> {
            celsius.set((val.doubleValue() - 32) * 5/9);
            kelvin.set(celsius.get() + 273.15);
        });
        kelvin.addListener((obs, old, val) -> {
            celsius.set(val.doubleValue() - 273.15);
            fahrenheit.set(celsius.get() * 9/5 + 32);
        });
    }

    public void setCelsius(double v) { celsius.set(v); }
    public void setFahrenheit(double v) { fahrenheit.set(v); }
    public void setKelvin(double v) { kelvin.set(v); }
}
