package Pojo;

public class Ubicación {

    private double Latitud;
    private double  Longitud;

    public Ubicación() {
    }

    public Ubicación(double latitud, double longitud) {
        Latitud = latitud;
        Longitud = longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }
}
