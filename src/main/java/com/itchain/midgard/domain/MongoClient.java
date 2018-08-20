package com.itchain.midgard.domain;

import com.itchain.midgard.common.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoClient extends MongoRepository<ReadWriteSet, String> {
}
