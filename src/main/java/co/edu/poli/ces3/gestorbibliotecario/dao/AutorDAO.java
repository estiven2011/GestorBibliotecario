package co.edu.poli.ces3.gestorbibliotecario.dao;

import java.util.List;

public class AutorDAO {

    private int id;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String fechaNacimiento;
    private List<Integer> librosEscritos;


    public AutorDAO(int id, String nombre, String apellido, String nacionalidad, String fechaNacimiento, List<Integer> librosEscritos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.librosEscritos = librosEscritos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Integer> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(List<Integer> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }

//    Metodo para agregar libros
    public void agregarLibro(int libroId) {
        this.librosEscritos.add(libroId);
    }
}
