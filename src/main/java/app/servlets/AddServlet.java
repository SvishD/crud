package app.servlets;

import app.service.DBException;
import app.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add", name = "addServlet")
public class AddServlet extends HttpServlet {

    DBService dbService = DBService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String pass = req.getParameter("password");

        if (login == null || pass == null || name == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            dbService.addUser(name,login,pass);
        } catch (DBException e) {
            e.printStackTrace();
        }


        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/CRUD_war_exploded/users");


    }
}
