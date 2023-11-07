package com.webhook.core.util;

import java.net.URL;

public class UrlUtils {

    public static boolean isUrlValid(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
