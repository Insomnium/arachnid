package net.ins.arachnid.dao.impl;

import net.ins.arachnid.dao.AudioDao;
import net.ins.arachnid.domain.TrackInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by ins on 5/24/15.
 */
@Repository
public class AudioDaoHibernateImpl implements AudioDao, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    private Collection<TrackInfo> files = new HashSet<>();

    @Value("${crawler.dbfile}")
    private String dbFilePath;

    @Override
    public void addInfo(TrackInfo trackInfo) {
        files.add(trackInfo);
    }

    @Override
    public void addInfos(Collection<TrackInfo> tracks) {
        for (TrackInfo track : tracks) {
            this.files.add(track);
        }
    }

    @Override
    public String toString() {
        return "AudioDao{" +
                "files=" + files +
                '}';
    }
}
