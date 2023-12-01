package Pojo;

import java.util.ArrayList;

public class Persona {

    String Nombre;
    String Apellido;
    int edad;
    String Correo;
    String Contrasenia;
    private ArrayList <Garito> ArrGaritos;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad, String correo, ArrayList<Garito> arrGaritos) {
        Nombre = nombre;
        Apellido = apellido;
        this.edad = edad;
        Correo = correo;
        ArrGaritos = arrGaritos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public ArrayList<Garito> getArrGaritos() {
        return ArrGaritos;
    }

    public void setArrGaritos(ArrayList<Garito> arrGaritos) {
        ArrGaritos = arrGaritos;
    }
}
