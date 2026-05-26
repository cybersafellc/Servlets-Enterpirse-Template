package org.nands.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.nands.app.config.SecurityConfig;
import org.nands.app.dto.CreateUserRequest;

import org.nands.app.exceptions.BadRequestException;
import org.nands.app.models.User;

import org.nands.app.services.UserService;
import org.nands.app.utils.CsrfUtil;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final UserService userService =
            new UserService();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        String csrfToken = CsrfUtil.generate();

        req.setAttribute(SecurityConfig.CSRF_TOKEN_NAME, csrfToken);
        HttpSession session = req.getSession();
        session.setAttribute(SecurityConfig.CSRF_TOKEN_NAME, csrfToken);

        req.getRequestDispatcher(
                "/WEB-INF/views/register.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String  csrf = (String) session.getAttribute(SecurityConfig.CSRF_TOKEN_NAME);
        String reqCsrf = req.getParameter(SecurityConfig.CSRF_TOKEN_NAME);

        System.out.println(reqCsrf);

        if(session == null || csrf == null || reqCsrf == null || !csrf.equals(reqCsrf)){
            throw new BadRequestException("csrf token invalid");
        }

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