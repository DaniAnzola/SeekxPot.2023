package Pojo;

public class Garito {

    String NombreGarito;
    String MusicaGarito;
    String UbicacionGarito;

    public Garito() {
    }

    public Garito(String nombreGarito, String musicaGarito, String ubicacionGarito) {
        NombreGarito = nombreGarito;
        MusicaGarito = musicaGarito;
        UbicacionGarito = ubicacionGarito;
    }

    public String getNombreGarito() {
        return NombreGarito;
    }

    public void setNombreGarito(String nombreGarito) {
        NombreGarito = nombreGarito;
    }

    public String getMusicaGarito() {
        return MusicaGarito;
    }

    public void setMusicaGarito(String musicaGarito) {
        MusicaGarito = musicaGarito;
    }

    public String getUbicacionGarito() {
        return UbicacionGarito;
    }

    public void setUbicacionGarito(String ubicacionGarito) {
        UbicacionGarito = ubicacionGarito;
    }
}
