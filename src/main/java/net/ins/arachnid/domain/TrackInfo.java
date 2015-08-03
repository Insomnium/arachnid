package net.ins.arachnid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ins on 5/17/15.
 */
@Document
public class TrackInfo extends FileInfo {

    @Id
    private String id;
    private String band;
    private String title;
    private Long number;
    private Long cueStartPos;
    private Album album;

    public TrackInfo() {
        super();
    }

    public TrackInfo(String name, String path, String extension, boolean dir, String band, String title, Long number, Album album) {
        super(path, extension, dir);
        this.band = band;
        this.title = title;
        this.number = number;
        this.album = album;
    }

    public TrackInfo(String path, String extension, boolean dir, String band, String title, Long number) {
        super(path, extension, dir);
        this.band = band;
        this.title = title;
        this.number = number;
    }

    public TrackInfo(String path, String extension, boolean dir, String band, String title, Long number, Long cueStartPos) {
        this(path, extension, dir, band, title, number);
        this.cueStartPos = cueStartPos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getCueStartPos() {
        return cueStartPos;
    }

    public void setCueStartPos(Long cueStartPos) {
        this.cueStartPos = cueStartPos;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrackInfo trackInfo = (TrackInfo) o;

        if (id != null ? !id.equals(trackInfo.id) : trackInfo.id != null) return false;
        if (band != null ? !band.equals(trackInfo.band) : trackInfo.band != null) return false;
        if (title != null ? !title.equals(trackInfo.title) : trackInfo.title != null) return false;
        if (number != null ? !number.equals(trackInfo.number) : trackInfo.number != null) return false;
        if (cueStartPos != null ? !cueStartPos.equals(trackInfo.cueStartPos) : trackInfo.cueStartPos != null)
            return false;
        return !(album != null ? !album.equals(trackInfo.album) : trackInfo.album != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (band != null ? band.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (cueStartPos != null ? cueStartPos.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrackInfo{" +
                "id='" + id + '\'' +
                ", band='" + band + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", cueStartPos=" + cueStartPos +
                ", album=" + album +
                '}';
    }
}
