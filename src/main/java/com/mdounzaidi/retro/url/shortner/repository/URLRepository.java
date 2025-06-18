package com.mdounzaidi.retro.url.shortner.repository;

import com.mdounzaidi.retro.url.shortner.model.URLEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface URLRepository extends MongoRepository<URLEntry, ObjectId> {
    URLEntry findByShortUrl(String shortUrl);
    URLEntry findByOriginalURL(String originalURL);
}
