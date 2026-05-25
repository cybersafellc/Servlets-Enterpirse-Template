package org.nands.app.utils;

import org.nands.app.config.SecurityConfig;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hash(
            String password) {

        return BCrypt.hashpw(
                password,
                BCrypt.gensalt(
                        SecurityConfig.BCRYPT_ROUNDS
                )
        );
    }

    public static boolean verify(
            String password,
            String hash) {

        return BCrypt.checkpw(
                password,
                hash
        );
    }
}