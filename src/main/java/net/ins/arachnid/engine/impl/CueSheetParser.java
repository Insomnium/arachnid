package net.ins.arachnid.engine.impl;

import jwbroek.cuelib.*;
import net.ins.arachnid.domain.Album;
import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;
import net.ins.arachnid.engine.MediaFileParser;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ins on 5/25/15.
 */
@Component("cueParser")
public class CueSheetParser implements MediaFileParser<TrackInfo> {
    @Override
    public Collection<TrackInfo> parseFile(File file) throws TrackParseException {
        Collection<TrackInfo> parseResult = null;
        try {
            CueSheet sheet = CueParser.parse(file);
            List<FileData> cueParts = sheet.getFileData();
            List<TrackData> trackData = cueParts.get(0).getTrackData();
            parseResult = new ArrayList<>(trackData.size());
            for (TrackData t : trackData) {
                Position pregap = t.getPregap();
                Position postgap = t.getPostgap();
                System.out.println(t);
                Album album = new Album("", null, Collections.EMPTY_LIST);
                parseResult.add(new TrackInfo(sheet.getPerformer(), file.getPath(), FilenameUtils.getExtension(file.getName()), false, t.getPerformer(), album));
            }
        } catch (IOException e) {
            throw new TrackParseException(e.getMessage(), e);
        }

        return parseResult;
    }
}
