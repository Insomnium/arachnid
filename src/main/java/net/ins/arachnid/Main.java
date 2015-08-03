package net.ins.arachnid;

import net.ins.arachnid.conf.Config;
import net.ins.arachnid.conf.Mongo;
import net.ins.arachnid.engine.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@Import({Config.class, Mongo.class})
@ImportResource("classpath:applicationContext.xml")
@EnableMongoRepositories
public class Main {

    public static void main(String[] args) throws IOException {
        long started = System.currentTimeMillis();
        ConfigurableApplicationContext appContext = SpringApplication.run(Main.class, args);
        Scanner scanner = appContext.getBean(Scanner.class);
        scanner.scan();
        System.out.println(String.format("Scanned in %s ms", System.currentTimeMillis() - started));

        // TODO: Add active-scan/in-file-db mode property (setting)
    }
}
