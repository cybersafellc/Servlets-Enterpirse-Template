package org.nands.app.config;

public class SecurityConfig {

    public static final int
            BCRYPT_ROUNDS = 12;

    public static final String
            SESSION_COOKIE_NAME =
            "JSESSIONID";

    public static final boolean
            COOKIE_HTTP_ONLY = true;

    public static final boolean
            COOKIE_SECURE = true;

    public static final String
            CSRF_TOKEN_NAME =
            "_csrf";

    public static final String
            JWT_SECRET =
            System.getenv("JWT_SECRET");

    public static final long
            JWT_EXPIRE =
            1000L * 60 * 60 * 24;

    private SecurityConfig() {
    }
}