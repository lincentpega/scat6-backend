package com.lincentpega.scat6backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import com.lincentpega.scat6backend.models.Sportsman;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DbConfig {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        mongoTemplate.indexOps(Sportsman.class)
            .ensureIndex(new Index()
                .on("firstName", Sort.Direction.ASC));
    }
}
