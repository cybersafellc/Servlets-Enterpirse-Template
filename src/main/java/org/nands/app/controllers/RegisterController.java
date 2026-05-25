package org.nands.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.nands.app.dto.CreateUserRequest;

import org.nands.app.models.User;

import org.nands.app.services.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController
        extends HttpServlet {

    private final UserService userService =
            new UserService();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/WEB-INF/views/register.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        CreateUserRequest request =
                new CreateUserRequest();

        request.setEmail(
                req.getParameter("email")
        );

        request.setPassword(
                req.getParameter("password")
        );

        request.setName(
                req.getParameter("name")
        );

        User user =
                userService.create(request);

        req.setAttribute(
                "success",
                "User created: "
                        + user.getEmail()
        );

        req.getRequestDispatcher(
                "/WEB-INF/views/register.jsp"
        ).forward(req, resp);
    }
}