package net.ins.arachnid;

import jwbroek.cuelib.*;
import net.ins.arachnid.api.ArachnidService;
import net.ins.arachnid.api.CrawlerCriteria;
import net.ins.arachnid.engine.Ant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.applet.AppletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class ArachnidApplication {

    private static final String SAMPLE_CUE_FILE_PATH = "/home/ins/lossless/2014 Siren Charms/In Flames - Siren Charms (Digibook Edition).cue";

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext appContext = SpringApplication.run(ArachnidApplication.class, args);
        ArachnidService arachnid = appContext.getBean(ArachnidService.class);
        Files.walkFileTree(Paths.get(arachnid.getRoot()), new Ant());
//        CueSheet sheet = CueParser.parse(FileUtils.openInputStream(new File(SAMPLE_CUE_FILE_PATH)));
//        List<FileData> fileData = sheet.getFileData();
//
//        List<TrackData> trackDataList = fileData.get(0).getTrackData();
//        for (TrackData trackData : trackDataList) {
//            System.out.println(trackData);
//        }
//        TrackData trackData = trackDataList.get(1);
//        Position pregap = trackData.getPregap();
//        Position postgap = trackData.getPostgap();
//
//        // TODO: 1. Create database with scan status
//        // TODO: 2. Add active-scan/in-file-db mode property (setting)
//
//        String debug = "debug";
    }
}
