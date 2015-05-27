package net.ins.arachnid.engine;

import net.ins.arachnid.domain.TrackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by ins on 5/25/15.
 */
@Service
public class MediaFileParserFactory<T extends TrackInfo> {

//    @Autowired
//    @Qualifier("parsers")
    @Resource(name = "parsers")
    private Map<String, MediaFileParser> parsers;

    @Autowired
    @Qualifier("defaultParser")
    private MediaFileParser defaultParser;

    public MediaFileParser<T> obtainParser(String extension) {
        MediaFileParser parser = parsers.get(extension.toLowerCase());
        return parser != null ? parser : defaultParser;
    }
}
