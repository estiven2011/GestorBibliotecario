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


@WebServlet(name="libroSrv", value = "/libros")
public class LibroSrv extends HttpServlet {

    private static ArrayList<LibroDAO> libros;


    @Override
    public void init() throws ServletException {

        System.out.println("LibroServlet inicializado");
        asegurarInicializacion();
        super.init();
    }

    public static void asegurarInicializacion() {
        if (libros == null || libros.isEmpty()) {
            libros = new ArrayList();

            libros.add(new LibroDAO(
                    1,
                    "Cien Años de Soledad",
                    1967,
                    "Realismo Mágico",
                    true,
                    417,
                    new String[]{"Español"},
                    1
            ));

            libros.add(new LibroDAO(
                    2,
                    "Conversación en la Catedral",
                    1969,
                    "Novela",
                    true,
                    600,
                    new String[]{"Español", "Inglés"},
                    2
            ));

            libros.add(new LibroDAO(
                    3,
                    "El Renacuajo Paseador",
                    1900,
                    "Cuento infantil",
                    true,
                    15,
                    new String[]{"Español"},
                    3
            ));

            libros.add(new LibroDAO(
                    4,
                    "La pobre viejecita",
                    1900,
                    "Cuento infantil",
                    true,
                    12,
                    new String[]{"Español"},
                    3
            ));

            libros.add(new LibroDAO(
                    5,
                    "La Fiesta del Chivo",
                    2000,
                    "Novela",
                    false,
                    528,
                    new String[]{"Español", "Francés", "Inglés"},
                    2
            ));
        }
    }

    public static ArrayList<LibroDAO> getLibros() {
        asegurarInicializacion();
        return libros;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        ArrayList<Object> librosConAutor = new ArrayList<>();

        // Obtenemos la lista de los autores desde el metodo getAutores en AutorSrv
        ArrayList<AutorDAO> listaAutores = AutorSrv.getAutores();


        if (listaAutores == null || listaAutores.isEmpty()) {
            System.out.println("⚠ No hay autores registrados en AutorSrv.");
        }


        // los parametros que vamos a recibir
        String filtroGenero = req.getParameter("genero");
        String filtroDisponible = req.getParameter("disponible");

        for (LibroDAO libro : this.libros) {
            String autorNombreCompleto = "Autor no encontrado";



            if (filtroGenero != null) {
                filtroGenero = filtroGenero.trim().toLowerCase();

                if (!libro.getGenero().toLowerCase().contains(filtroGenero)) {
                    continue;
                }
            }

            // Filtro por disponibilidad
            if (filtroDisponible != null) {
                boolean disponibleParam = Boolean.parseBoolean(filtroDisponible);
                if (disponibleParam && !libro.isDisponible()) {
                    continue;
                }
                if (!disponibleParam && libro.isDisponible()) {
                    continue;
                }
            }


            for (AutorDAO autor : listaAutores) {
                if (autor.getId() == libro.getAutorId()) {
                    autorNombreCompleto = autor.getNombre() + " " + autor.getApellido();
                    break;
                }
            }

            // Crear un HashMap con los datos de los libros  y el nombre del autor
            HashMap<String, Object> libroConAutor = new HashMap<>();
            libroConAutor.put("id", libro.getId());
            libroConAutor.put("titulo", libro.getTitulo());
            libroConAutor.put("anioPublicacion", libro.getAnioPublicacion());
            libroConAutor.put("genero", libro.getGenero());
            libroConAutor.put("disponible", libro.isDisponible());
            libroConAutor.put("numeroPaginas", libro.getNumeroPaginas());
            libroConAutor.put("idiomas", libro.getIdiomas());
            libroConAutor.put("autorNombreCompleto", autorNombreCompleto);

            librosConAutor.add(libroConAutor);
        }

        out.print(gson.toJson(librosConAutor));
        out.flush();
    }

}
