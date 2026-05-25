package org.nands.app.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.annotation.WebListener;

import org.nands.app.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AppContextListener
        implements ServletContextListener {

    private static final Logger log =
            LoggerFactory.getLogger(
                    AppContextListener.class
            );

    @Override
    public void contextInitialized(
            ServletContextEvent sce) {

        log.info(
                "Application starting..."
        );

        try {

            log.info(
                    "Loading configuration..."
            );

            log.info(
                    "Initializing database pool..."
            );

            // force initialize datasource
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            log.info(
                    "Application started successfully"
            );

            // SESSION TIMEOUT

            sce.getServletContext()
                    .setSessionTimeout(
                            SecurityConfig.SESSION_TIMEOUT
                    );

            // COOKIE CONFIG

            SessionCookieConfig cookieConfig =
                    sce.getServletContext()
                            .getSessionCookieConfig();

            cookieConfig.setHttpOnly(
                    SecurityConfig.COOKIE_HTTP_ONLY
            );

            cookieConfig.setSecure(
                    SecurityConfig.COOKIE_SECURE
            );

            cookieConfig.setName(
                    SecurityConfig.SESSION_COOKIE_NAME
            );

        } catch (Exception e) {

            log.error(
                    "Application startup failed",
                    e
            );

            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(
            ServletContextEvent sce) {

        log.info(
                "Application shutting down..."
        );

        log.info(
                "Application stopped"
        );
    }
}