package net.ins.arachnid.crawler;

import net.ins.arachnid.domain.FIleInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ins on 5/17/15.
 */
@RestController
public class ArachnidController {

    @Autowired
    private CrawlerCriteria fsFilter;

    @RequestMapping(PathUtil.PATH_URL_MAPPING + "/**")
    public ResponseEntity<List<FIleInfo>> listDirectory(HttpServletRequest request) {
        String path = PathUtil.extractPath(request);

        List<FIleInfo> result = new ArrayList<>();
        try {
            DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path), fsFilter);
            for (Path d : dir) {
                File f = d.toFile();
                result.add(new FIleInfo(f.getName(), d.toString(), FilenameUtils.getExtension(f.getName()), f.isDirectory()));
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
