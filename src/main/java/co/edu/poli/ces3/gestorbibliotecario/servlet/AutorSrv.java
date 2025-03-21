package co.edu.poli.ces3.gestorbibliotecario.servlet;


import co.edu.poli.ces3.gestorbibliotecario.dao.AutorDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name="autorSrv", value="/autores")
public class AutorSrv extends HttpServlet {

    private ArrayList<AutorDAO> autores;


    @Override
    public void init() throws ServletException {
        System.out.println("LibroServlet inicializado");

        this.autores = new ArrayList<>();
        this.autores.add(new AutorDAO(
                1,
                "Gabriel",
                "García Márquez",
                "Colombiana",
                "1927-03-06",
                new ArrayList<>()
        ));  this.autores.add(new AutorDAO(
                2,
                "Mario",
                "Vargas Llosa",
                "Peruana",
                "1936-03-28",
                new ArrayList<>()
        ));

        super.init();

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        out.print(gson.toJson(autores));

        out.flush();
    }




}
