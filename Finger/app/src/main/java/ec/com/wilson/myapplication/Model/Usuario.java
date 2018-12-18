package ec.com.wilson.myapplication.Model;

/**
 * Entidad definida para manejo de usuarios
 */
public class Usuario {

    private String user;
    private String password;
    private String nombre;
    private String apellido;
    private String cedula;

    public Usuario() {
    }

    public Usuario(String user, String password, String nombre, String apellido, String cedula) {
        this.user = user;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                '}';
    }
}
