package net.ins.arachnid.internals;

import net.ins.arachnid.domain.TrackInfo;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ins on 5/24/15.
 */
public class FSStorage implements Serializable {
    private Collection<TrackInfo> files = new ArrayList<>();

    @Value("${crawler.dbfile}")
    private String dbFilePath;

    public void addInfo(TrackInfo trackInfo) {
        if (!this.files.contains(trackInfo)) {
            files.add(trackInfo);
        }
    }

    public void addInfos(Collection<TrackInfo> tracks) {
        for (TrackInfo track : tracks) {
            if (!this.files.contains(track)) {
                this.files.add(track);
            }
        }
    }

    @Override
    public String toString() {
        return "FSStorage{" +
                "files=" + files +
                '}';
    }
}
