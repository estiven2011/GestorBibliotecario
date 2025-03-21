package co.edu.poli.ces3.gestorbibliotecario.servlet;

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


@WebServlet(name="libroSrv", value = "/libros")
public class LibroSrv extends HttpServlet {

    private ArrayList<LibroDAO> libros;

    @Override
    public void init() throws ServletException {

        System.out.println("LibroServlet inicializado");

        this.libros = new ArrayList();

        this.libros.add(new LibroDAO(
                1,
                "Cien Años de Soledad",
                1967,
                "Realismo Mágico",
                true,
                417,
                new String[]{"Español"},
                1
        ));

        this.libros.add(new LibroDAO(
                2,
                "Conversación en la Catedral",
                1969,
                "Novela",
                true,
                600,
                new String[]{"Español", "Inglés"},
                2
        ));

        this.libros.add(new LibroDAO(
                3,
                "El Renacuajo Paseador",
                1900,
                "Cuento infantil",
                true,
                15,
                new String[]{"Español"},
                3
        ));

        this.libros.add(new LibroDAO(
                4,
                "La pobre viejecita",
                1900,
                "Cuento infantil",
                true,
                12,
                new String[]{"Español"},
                3
        ));

        this.libros.add(new LibroDAO(
                5,
                "La Fiesta del Chivo",
                2000,
                "Novela histórica",
                false,
                528,
                new String[]{"Español", "Francés", "Inglés"},
                2
        ));


        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        out.print(gson.toJson(libros));

        out.flush();
    }




}
