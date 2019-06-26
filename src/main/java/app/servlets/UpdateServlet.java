package app.servlets;

import app.service.DBException;
import app.service.DBService;
import app.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update", name = "updateServlet")
public class UpdateServlet extends HttpServlet {

    private DBService dbService = DBService.getInstance();
    String id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = null;
        id = req.getParameter("id");
        try {
            user = dbService.getUser(Long.valueOf(id));
        } catch (DBException e) {
            e.printStackTrace();
        }

        req.setAttribute("user",user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null || pass == null || name == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            dbService.updateUser(id,name,login,pass);
        } catch (DBException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/users");

    }
}
