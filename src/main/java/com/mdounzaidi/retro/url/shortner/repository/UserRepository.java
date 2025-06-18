package com.mdounzaidi.retro.url.shortner.repository;

import com.mdounzaidi.retro.url.shortner.model.User;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByUserName(@NonNull String userName);

    User getUsersByUserName(String userName);
}
