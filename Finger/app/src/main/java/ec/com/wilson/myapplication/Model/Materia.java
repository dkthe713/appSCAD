package ec.com.wilson.myapplication.Model;

public class Materia {

    String idMateria;
    String nombre;

    public Materia() {
    }

    public Materia(String idMateria, String nombre) {
        this.idMateria = idMateria;
        this.nombre = nombre;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "idMateria='" + idMateria + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
