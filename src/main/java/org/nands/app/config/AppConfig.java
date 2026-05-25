package org.nands.app.config;

public class AppConfig {

    public static final String
            APP_NAME = "Enterprise Servlet App";

    public static final String VIEW = "WEB-INF/views/";

    public static final String
            APP_VERSION = "1.0.0";

    public static final int
            SESSION_TIMEOUT = 1800;

    public static final int
            MAX_UPLOAD_SIZE =
            10 * 1024 * 1024;

    public static final boolean
            DEBUG =
            Boolean.parseBoolean(
                    System.getenv()
                            .getOrDefault(
                                    "APP_DEBUG",
                                    "false"
                            )
            );

    private AppConfig() {
    }
}