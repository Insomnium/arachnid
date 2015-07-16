package net.ins.arachnid.conf;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.h2.tools.Script;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Created by ins on 7/16/15.
 */
@Configuration
public class Config {

    @Value("jdbc:h2:file:${db.dir}/${db.file};TRACE_LEVEL_SYSTEM_OUT=3")
    private String url;

    @Value("${db.dir}/${db.file}")
    private String dbFilePath;

    @Value("${jdbc.user}")
    private String dbUser;

    @Value("${jdbc.password}")
    private String dbPassword;

    @PostConstruct
    public void init() throws IOException, SQLException {
        File dbFile = Paths.get(dbFilePath).toFile();
        if (!dbFile.exists()) {
            dbFile.createNewFile();
        }

        URL resource = getClass().getResource("/db/audio.sql");
        String path = resource.getPath();

        RunScript.execute(url, dbUser, dbPassword, path, Charset.forName("UTF-8"), false);
    }

    @Bean(name = "dataSource")
    public JdbcDataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(url);
        ds.setPassword(dbPassword);
        return ds;
    }
}
