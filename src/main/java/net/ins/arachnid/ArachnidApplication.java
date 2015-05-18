package net.ins.arachnid;

import jwbroek.cuelib.*;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ArachnidApplication {

    private static final String SAMPLE_CUE_FILE_PATH = "/home/ins/lossless/2014 Siren Charms/In Flames - Siren Charms (Digibook Edition).cue";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ArachnidApplication.class, args);
        CueSheet sheet = CueParser.parse(FileUtils.openInputStream(new File(SAMPLE_CUE_FILE_PATH)));
        List<FileData> fileData = sheet.getFileData();

        List<TrackData> trackDataList = fileData.get(0).getTrackData();
        for (TrackData trackData : trackDataList) {
            System.out.println(trackData);
        }
        TrackData trackData = trackDataList.get(1);
        Position pregap = trackData.getPregap();
        Position postgap = trackData.getPostgap();

        // TODO: 1. Create database with scan status
        // TODO: 2. Add active-scan/in-file-db mode property (setting)

        String debug = "debug";
    }
}
