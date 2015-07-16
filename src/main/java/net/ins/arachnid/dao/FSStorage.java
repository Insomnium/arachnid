package net.ins.arachnid.dao;

import net.ins.arachnid.domain.TrackInfo;

import java.util.Collection;

/**
 * Created by ins on 7/16/15.
 */
public interface FSStorage {
    void addInfo(TrackInfo trackInfo);
    void addInfos(Collection<TrackInfo> tracks);
}
