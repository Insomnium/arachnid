package net.ins.arachnid.domain;

/**
 * Created by ins on 5/17/15.
 */
public class TrackInfo extends FIleInfo {
    private String band;
    private Album album;

    public TrackInfo() {
        super();
    }

    public TrackInfo(String name, String path, String extension, boolean dir, String band, Album album) {
        super(name, path, extension, dir);
        this.band = band;
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrackInfo trackInfo = (TrackInfo) o;

        if (band != null ? !band.equals(trackInfo.band) : trackInfo.band != null) return false;
        return !(album != null ? !album.equals(trackInfo.album) : trackInfo.album != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (band != null ? band.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrackInfo{" +
                "band='" + band + '\'' +
                ", album=" + album +
                "} " + super.toString();
    }
}
