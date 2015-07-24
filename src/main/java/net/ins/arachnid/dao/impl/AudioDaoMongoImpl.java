package net.ins.arachnid.dao.impl;

import net.ins.arachnid.dao.AudioDao;
import net.ins.arachnid.domain.TrackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;

/**
 * Created by ins on 7/24/15.
 */
public class AudioDaoMongoImpl implements AudioDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addInfo(TrackInfo trackInfo) {
        mongoTemplate.save(trackInfo);
    }

    @Override
    public void addInfos(Collection<TrackInfo> tracks) {
        for (TrackInfo track : tracks) {
            addInfo(track);
        }

    }
}
