package net.ins.arachnid.engine;

import net.ins.arachnid.domain.ParseResult;
import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;

import java.io.File;
import java.util.Collection;

/**
 * Created by ins on 5/25/15.
 */
public interface MediaFileParser<T extends TrackInfo> {
    ParseResult parse(File file) throws TrackParseException;
}
