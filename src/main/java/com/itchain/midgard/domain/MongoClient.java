package com.itchain.midgard.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoClient extends MongoRepository<EntityWithIdAndEventList, String> {
}
