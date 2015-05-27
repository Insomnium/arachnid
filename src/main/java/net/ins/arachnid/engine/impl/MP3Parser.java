package net.ins.arachnid.engine.impl;

import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;
import net.ins.arachnid.engine.MediaFileParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;

/**
 * Created by ins on 5/26/15.
 */
@Component("mp3Parser")
public class MP3Parser implements MediaFileParser<TrackInfo> {
    @Override
    public Collection<TrackInfo> parseFile(File file) throws TrackParseException {
        throw new UnsupportedOperationException("Not supported!");
    }
}
