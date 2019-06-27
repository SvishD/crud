package app.servlets;

import app.service.UserException;
import app.service.UserService;
import app.service.UserServiceImpl;
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

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = null;
        try {
            users = userService.getUsers();
        } catch (UserException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/users.jsp");
        requestDispatcher.forward(req, resp);
    }
}
