package org.nands.app.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

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