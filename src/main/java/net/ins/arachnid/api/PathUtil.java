package net.ins.arachnid.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ins on 5/17/15.
 */
public class PathUtil {
    public static final String PATH_URL_MAPPING = "/path";
    public static final String PATH_URL_JSON_POSTFIX = ".json";

    public static String extractPath(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        if (StringUtils.isNotEmpty(path)) {
            String p = path.substring(PATH_URL_MAPPING.length(), path.length());
            if (path.endsWith(PATH_URL_JSON_POSTFIX)) {
                return p.substring(0, p.length() - PATH_URL_JSON_POSTFIX.length());
            }
            return p;
        }

        throw new IllegalArgumentException("Request does not contain path");
    }
}
