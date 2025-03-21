package co.edu.poli.ces3.gestorbibliotecario.servlet;

import co.edu.poli.ces3.gestorbibliotecario.dao.AutorDAO;
import co.edu.poli.ces3.gestorbibliotecario.dao.LibroDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name="autorSrv", value="/autores")
public class AutorSrv extends HttpServlet {

    public static ArrayList<AutorDAO> autores;

    @Override
    public void init() throws ServletException {
        System.out.println("AutorServlet inicializado");


        super.init();
    }


    public static void asegurarInicializacion() {
        if (autores == null || autores.isEmpty()) {
            autores = new ArrayList();


            autores.add(new AutorDAO(
                    1,
                    "Gabriel",
                    "García Márquez",
                    "Colombiana",
                    "1927-03-06",
                    new ArrayList<>()
            ));

            autores.add(new AutorDAO(
                    2,
                    "Mario",
                    "Vargas Llosa",
                    "Peruana",
                    "1936-03-28",
                    new ArrayList<>()
            ));

            autores.add(new AutorDAO(
                    3,
                    "Rafael",
                    "Pombo Rebolledo",
                    "Colombiana",
                    "1833-11-07",
                    new ArrayList<>()
            ));

        }
    }


    //Metodo para obtener los autores globalmente
    public static ArrayList<AutorDAO> getAutores() {
        asegurarInicializacion();
        return autores;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        ArrayList<Object> autoresConLibros = new ArrayList<>();

        // Obtenemos la lista de los libros desde el metodo getLibros en LibroSrv
        ArrayList<LibroDAO> libros = LibroSrv.getLibros();

        //Recorremos todos los autores y se compara los id de autores y los libros
        for (AutorDAO autor : autores) {
            ArrayList<String> librosEscritos = new ArrayList<>();

            for (LibroDAO libro : libros) {
                if (libro.getAutorId() == autor.getId()) {
                    librosEscritos.add(libro.getTitulo());
                }
            }

            // Crear un HashMap con los datos del autor y los libros escritos por este autor
            HashMap<String, Object> autorConLibros = new HashMap<>();
            autorConLibros.put("id", autor.getId());
            autorConLibros.put("nombre", autor.getNombre());
            autorConLibros.put("apellido", autor.getApellido());
            autorConLibros.put("nacionalidad", autor.getNacionalidad());
            autorConLibros.put("fechaNacimiento", autor.getFechaNacimiento());
            autorConLibros.put("librosEscritos", librosEscritos);
            autoresConLibros.add(autorConLibros);
        }

        out.print(gson.toJson(autoresConLibros));
        out.flush();
    }
}
