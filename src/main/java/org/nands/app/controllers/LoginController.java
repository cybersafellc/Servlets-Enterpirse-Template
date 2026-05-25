package org.nands.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.nands.app.config.AppConfig;
import org.nands.app.dto.UserLogin;
import org.nands.app.models.User;
import org.nands.app.services.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getContextPath());
        req.getRequestDispatcher(AppConfig.VIEW + "login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLogin user = new UserLogin();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));

        User userExist = userService.login(user);

        req.changeSessionId();

        HttpSession session = req.getSession();

        session.setAttribute("user", userExist);
        session.setAttribute("role", userExist.getRole());

        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }
}
