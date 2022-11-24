package by.issoft.server;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HTTPHelper {
    public static Map<String, String> queryToMap(String query) {
        if (query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.substring(query.indexOf("?")+1).split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0].toLowerCase(Locale.ROOT), entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
