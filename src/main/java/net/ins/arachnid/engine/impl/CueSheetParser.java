package net.ins.arachnid.engine.impl;

import jwbroek.cuelib.*;
import net.ins.arachnid.domain.ParseResult;
import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;
import net.ins.arachnid.engine.MediaFileParser;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ins on 5/25/15.
 */
@Component("cueParser")
public class CueSheetParser implements MediaFileParser<TrackInfo> {
    @Override
    public ParseResult parse(File file) throws TrackParseException {
        List<TrackInfo> tracks = new ArrayList<>();
        try {
            CueSheet sheet = CueParser.parse(file);
            List<FileData> cueParts = sheet.getFileData();
            List<TrackData> trackData = cueParts.get(0).getTrackData();
            for (TrackData data : trackData) {
                System.out.println(data);
                TrackInfo track = new TrackInfo(file.getPath(),
                        FilenameUtils.getExtension(file.getName()), false, data.getPerformer(), data.getTitle(),
                        (long) data.getNumber());
                fillTiming(track, data);
                tracks.add(track);
            }
        } catch (IOException e) {
            throw new TrackParseException(e.getMessage(), e);
        }

        return new ParseResult(tracks);
    }

    private void fillTiming(TrackInfo trackInfo, TrackData data) {
        Position pregap = data.getPregap();
        if (pregap != null) {
            trackInfo.setCueStartPos((long) ((pregap.getMinutes() * 60) + pregap.getSeconds()));
        } else if (!CollectionUtils.isEmpty(data.getIndices())) {
            Position position = data.getIndices().get(0).getPosition();
            if (position != null) {
                trackInfo.setCueStartPos((long) ((position.getMinutes() * 60) + position.getSeconds()));
            }
        }
    }
}
