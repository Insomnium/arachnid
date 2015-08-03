package net.ins.arachnid.domain;

import java.util.Collections;
import java.util.List;

/**
 * Created by ins on 8/3/15.
 */
public class ParseResult {
    private Album album;
    private List<TrackInfo> tracks;

    public ParseResult() {
    }

    public ParseResult(Album album, List<TrackInfo> tracks) {
        this.album = album;
        this.tracks = tracks;
    }

    public ParseResult(List<TrackInfo> tracks) {
        this.tracks = tracks;
    }

    public ParseResult(TrackInfo track) {
        this.tracks = Collections.singletonList(track);
    }

    public ParseResult(Album album, TrackInfo trackInfo) {
        this(trackInfo);
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public List<TrackInfo> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "ParseResult{" +
                "album=" + album +
                ", tracks=" + tracks +
                '}';
    }
}
