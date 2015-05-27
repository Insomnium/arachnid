package net.ins.arachnid.internals;

import net.ins.arachnid.domain.Album;
import net.ins.arachnid.domain.TrackInfo;

import java.util.Collection;

/**
 * Created by ins on 5/24/15.
 */
public class FSRecord {
    private String band;
    private Album album;
    private Collection<TrackInfo> folderTracks;

    public FSRecord(String band, Album album, Collection<TrackInfo> folderTracks) {
        this.band = band;
        this.album = album;
        this.folderTracks = folderTracks;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Collection<TrackInfo> getFolderTracks() {
        return folderTracks;
    }

    public void setFolderTracks(Collection<TrackInfo> folderTracks) {
        this.folderTracks = folderTracks;
    }

    @Override
    public String toString() {
        return "FSRecord{" +
                "band='" + band + '\'' +
                ", album=" + album +
                ", folderTracks=" + folderTracks +
                '}';
    }
}
