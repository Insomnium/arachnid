package net.ins.arachnid.crawler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ins on 5/17/15.
 */
public class PathUtil {
    public static final String PATH_URL_MAPPING = "/path";

    public static String extractPath(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        if (StringUtils.isNotEmpty(path)) {
            return path.substring(PATH_URL_MAPPING.length(), path.length());
        }

        throw new IllegalArgumentException("Request does not contain path");
    }
}
