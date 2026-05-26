package org.nands.app.utils;

import java.util.UUID;

public class CsrfUtil {
    public static String generate(){
        return String.valueOf(UUID.randomUUID());
    }
}
