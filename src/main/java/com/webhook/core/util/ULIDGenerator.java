package com.webhook.core.util;

import com.github.f4b6a3.ulid.UlidCreator;

public class ULIDGenerator {
    public static String generate() {
        return UlidCreator.getUlid().toLowerCase();
    }
}
