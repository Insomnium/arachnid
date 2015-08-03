package net.ins.arachnid.api;

import net.ins.arachnid.domain.FileInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ins on 5/17/15.
 */
@RestController
public class AntController implements ApplicationContextAware {

    @Autowired
    private CrawlerCriteria fsFilter;

    @Value("${crawler.rootfolder}")
    private String root;

    private ApplicationContext applicationContext;

    @RequestMapping(value = PathUtil.PATH_URL_MAPPING + "/**", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<FileInfo>> listDirectory(HttpServletRequest request) {
        String path = PathUtil.extractPath(request);

        path = StringUtils.isEmpty(path) || "/".equals(path) ? root : path;
        if (path.length() < root.length()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<FileInfo> result = new ArrayList<>();
//        if (Paths.get(path).toFile().isFile() && fsFilter.getExtensions().contains(FilenameUtils.getExtension(path))) {
//        } else {
        try {
            DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path), fsFilter);
            for (Path d : dir) {
                File f = d.toFile();
                result.add(new FileInfo(d.toString(), FilenameUtils.getExtension(f.getName()), f.isDirectory()));
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = PathUtil.PATH_URL_MAPPING + "/**", method = { RequestMethod.GET, RequestMethod.HEAD })
    public ResponseEntity getTrack(HttpServletRequest request) throws Exception {
        String path = PathUtil.extractPath(request);
        if (Paths.get(path).toFile().isFile() && fsFilter.getExtensions().contains(FilenameUtils.getExtension(path))) {
            Resource resource = applicationContext.getResource("file://" + path);
            InputStream inputStream = resource.getInputStream();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentLength(inputStream.available());
            responseHeaders.add("Content-Disposition", String.format("attachment; filename=%s", path.substring(path.lastIndexOf("/") + 1, path.length())));
            if (RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
                InputStreamResource isr = new InputStreamResource(inputStream);
                return new ResponseEntity(isr, responseHeaders, HttpStatus.OK);
            }
            return new ResponseEntity(responseHeaders, HttpStatus.OK); // Need HEAD request type due to aurora.js requirements
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
