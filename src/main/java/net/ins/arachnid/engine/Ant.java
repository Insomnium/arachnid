package net.ins.arachnid.engine;

import net.ins.arachnid.dao.impl.AudioDaoRepo;
import net.ins.arachnid.domain.ParseResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by ins on 5/20/15.
 */
@Service
@Scope("prototype")
public class Ant extends Scanner {

    private static final Logger logger = Logger.getLogger(Ant.class);

    @Value("${crawler.dbfile}")
    private String dbFilePath;

    @Value("#{'${crawler.extensions}'.split('\\|')}")
    private List<String> extensions;

    @Autowired
    private MediaFileParserFactory parserFactory;

    @Autowired
    private AudioDaoRepo audioDao;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        String extension = FilenameUtils.getExtension(f.getName());
        if (!extensions.contains(extension.toLowerCase())) {
            return FileVisitResult.CONTINUE;
        }
        if (attrs.isSymbolicLink()) {
            logger.debug(f.getAbsolutePath() + " <- Symbolic link");
        } else if (attrs.isRegularFile()) {
            logger.debug(f.getAbsolutePath());
            MediaFileParser parser = parserFactory.obtainParser(FilenameUtils.getExtension(f.getName()));
            ParseResult parseResult = parser.parse(f);
            if (!CollectionUtils.isEmpty(parseResult.getTracks())) {
                audioDao.save(parseResult.getTracks());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        logger.error(String.format("======= FAILED AT FILE: %s =======", file.toFile().getAbsolutePath()));
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
