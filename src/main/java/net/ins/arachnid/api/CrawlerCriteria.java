package net.ins.arachnid.api;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by ins on 5/18/15.
 */
@Component
public class CrawlerCriteria implements DirectoryStream.Filter<Path> {

    @Value("#{'${crawler.extensions}'.split('\\|')}")
    private List<String> extensions;

    @Override
    public boolean accept(Path entry) throws IOException {
        String ext = FilenameUtils.getExtension(entry.toFile().getName());
        return Files.isDirectory(entry) || extensions.contains(ext);
    }

    public List<String> getExtensions() {
        return extensions;
    }
}
