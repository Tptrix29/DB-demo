package cn.edu.tongji.sse.db.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public GridFSBucket createBucket(
            MongoClient mongoClient,
            @Value("${file.db}") String db,
            @Value("${file.bucket}") String bucketName){
        MongoDatabase database = mongoClient.getDatabase(db);
        return GridFSBuckets.create(database, bucketName);
    }

    @Bean
    public GridFSUploadOptions createUploadOptions(){
        return new GridFSUploadOptions();
    }

}
