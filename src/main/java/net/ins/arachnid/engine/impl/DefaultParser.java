package net.ins.arachnid.engine.impl;

import net.ins.arachnid.domain.ParseResult;
import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;
import net.ins.arachnid.engine.MediaFileParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;

/**
 * Created by ins on 5/26/15.
 */
@Component("defaultParser")
public class DefaultParser implements MediaFileParser<TrackInfo> {
    @Override
    public ParseResult parse(File file) throws TrackParseException {
        return new ParseResult();
    }
}
