package app.servlets;

import app.exceptions.UserException;
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

@WebServlet(urlPatterns = "/update", name = "updateServlet")
public class UpdateServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = null;
        String id = req.getParameter("id");
        try {
            user = userService.get(Long.valueOf(id));
        } catch (UserException e) {
            e.printStackTrace();
        }

        req.setAttribute("user",user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null || pass == null || name == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            userService.updateUser(new User(Long.valueOf(id),name,login,pass));
        } catch (UserException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/users");

    }
}
