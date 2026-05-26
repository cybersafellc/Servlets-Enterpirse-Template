package org.nands.app.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nands.app.exceptions.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

@WebFilter("/*")
public class GlobalExceptionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        try {
            System.out.println("hello logging filter..");

            String method = req.getMethod();

            String uri = req.getRequestURI();

            String ip = req.getRemoteAddr();

            log.info(
                    "{} {} from {}",
                    method,
                    uri,
                    ip
            );

            chain.doFilter(request, response);

        } catch (AppException e) {

            log.warn(
                    "Application error: {}",
                    e.getMessage()
            );

            resp.setStatus(
                    e.getStatusCode()
            );

            req.setAttribute(
                    "message",
                    e.getMessage()
            );

            req.setAttribute(
                    "status",
                    e.getStatusCode()
            );

            req.getRequestDispatcher(
                    "/WEB-INF/views/error.jsp"
            ).forward(req, resp);

        } catch (Exception e) {

            log.error(
                    "Unexpected error",
                    e
            );

            resp.setStatus(500);

            req.setAttribute(
                    "message",
                    "Internal server error"
            );

            req.setAttribute(
                    "status",
                    500
            );

            req.getRequestDispatcher(
                    "/WEB-INF/views/error.jsp"
            ).forward(req, resp);
        }
    }
}