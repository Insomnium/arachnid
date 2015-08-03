package net.ins.arachnid.dao.impl;

import net.ins.arachnid.domain.TrackInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ins on 8/3/15.
 */
public interface AudioDaoRepo extends MongoRepository<TrackInfo, String> {
    List<TrackInfo> findByBand(String band);
}
