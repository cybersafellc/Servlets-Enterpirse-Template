package org.nands.app.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter
        implements Filter {

    private static final Logger log =
            LoggerFactory.getLogger(
                    LoggingFilter.class
            );

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        String method =
                req.getMethod();

        String uri =
                req.getRequestURI();

        String ip =
                req.getRemoteAddr();

        log.info(
                "{} {} from {}",
                method,
                uri,
                ip
        );

        chain.doFilter(request, response);
    }
}