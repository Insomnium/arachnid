package net.ins.arachnid.engine;

import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.internals.FSRecord;
import net.ins.arachnid.internals.FSStorage;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.List;

/**
 * Created by ins on 5/20/15.
 */
@Component
@Scope("prototype")
public class Ant extends Scanner {

    private static final Logger logger = Logger.getLogger(Ant.class);
    public static final String DEFAULT_DB_FILE = "~/.arachniddb";


    @Value("${crawler.dbfile}")
    private String dbFilePath;

    @Value("#{'${crawler.extensions}'.split('\\|')}")
    private List<String> extensions;

    @Autowired
    private MediaFileParserFactory parserFactory;

    private FSStorage storage = new FSStorage();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        String extension = FilenameUtils.getExtension(f.getName());
        if (!extensions.contains(extension.toLowerCase())) {
            return FileVisitResult.CONTINUE;
        }
        if (attrs.isSymbolicLink()) {
            System.out.println(f.getAbsolutePath() + " <- Symbolic link");
        } else if (attrs.isRegularFile()) {
            System.out.println(f.getAbsolutePath());
            MediaFileParser parser = parserFactory.obtainParser(FilenameUtils.getExtension(f.getName()));
            Collection<TrackInfo> result = parser.parseFile(f);
            storage.addInfos(result);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println(String.format("======= FAILED AT FILE: %s =======", file.toFile().getAbsolutePath()));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public void scan() {
        try {
            Files.walkFileTree(Paths.get(root), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
