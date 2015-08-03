package net.ins.arachnid.engine.impl;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import net.ins.arachnid.domain.Album;
import net.ins.arachnid.domain.ParseResult;
import net.ins.arachnid.domain.TrackInfo;
import net.ins.arachnid.domain.TrackParseException;
import net.ins.arachnid.engine.MediaFileParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * Created by ins on 5/26/15.
 */
@Component("mp3Parser")
public class MP3Parser implements MediaFileParser<TrackInfo> {

    private ThreadLocal<DateFormat> dateFormatter = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy");
        }
    };

    @Override
    public ParseResult parse(File file) throws TrackParseException {
        String path = StringUtils.EMPTY;
        try {
            path = file.getAbsolutePath();
            Mp3File mp3 = new Mp3File(path);
            ID3v1 meta = mp3.getId3v1Tag();
            Album album = new Album(meta.getAlbum(), extractReleaseDate(meta), null);
            TrackInfo track = new TrackInfo(file.getName(), path, FilenameUtils.getExtension(file.getName()),
                    false, meta.getArtist(), meta.getTitle(), null, album);

            return new ParseResult(album, track);
        } catch (IOException e) {
            throw new TrackParseException("Failed reading file from path: " + path);
        } catch (UnsupportedTagException e) {
            throw new TrackParseException("Error occurred while parsing MP3 file: " + path);
        } catch (InvalidDataException e) {
            throw new TrackParseException("Audio file seems broken or unsupported: " + path);
        }
    }

    private Date extractReleaseDate(ID3v1 metadata)  {
        if (StringUtils.isNotEmpty(metadata.getYear())) {
            try {
                return dateFormatter.get().parse(metadata.getYear());
            } catch (ParseException e) {
                // TODO: log it
            }
        }

        return null;
    }
}
