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
import java.util.List;

@WebServlet(urlPatterns = "/users", name = "userManagementServlet")
public class UserManagementServlet extends HttpServlet {

    private DBService dbService = DBService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if(!(id == null)){
            try {
                dbService.deleteUser(id);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

        List<User> users = null;
        try {
            users = dbService.getUsers();
        } catch (DBException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/users.jsp");
        requestDispatcher.forward(req, resp);
    }
}
