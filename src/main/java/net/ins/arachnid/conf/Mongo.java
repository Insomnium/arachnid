package net.ins.arachnid.conf;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by ins on 7/23/15.
 */
@Configuration
@EnableMongoRepositories
public class Mongo extends AbstractMongoConfiguration {

    @Value("${crawler.mongo.dbname}")
    private String mongoDbName;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public com.mongodb.Mongo mongo() throws Exception {
        return new MongoClient("localhost", 27017);
    }
}
