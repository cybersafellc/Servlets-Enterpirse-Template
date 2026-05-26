package org.nands.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.nands.app.config.AppConfig;
import org.nands.app.models.User;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getAttribute("user"));
        req.getRequestDispatcher(AppConfig.VIEW + "users.jsp").forward(req, resp);
    }
}
