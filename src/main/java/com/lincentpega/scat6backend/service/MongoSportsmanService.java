package com.lincentpega.scat6backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lincentpega.scat6backend.models.Sportsman;
import com.lincentpega.scat6backend.mapper.SportsmanMapper;
import com.lincentpega.scat6backend.dto.PagedResult;
import com.lincentpega.scat6backend.dto.SportsmanDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MongoSportsmanService implements SportsmanService {

    private static final String CASE_INSENSITIVE_REGEX = "i";

    private final MongoTemplate mongoTemplate;
    private final SportsmanMapper sportsmanMapper;

    @Override
    public Optional<SportsmanDto> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Sportsman.class))
                .map(sportsmanMapper::toDto);
    }

    @Override
    public PagedResult<SportsmanDto> searchByFullName(String fullNamePart, Integer page, Integer limit) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fullName").regex("^" + fullNamePart, CASE_INSENSITIVE_REGEX));
        long totalItems = mongoTemplate.count(query, Sportsman.class);
        query.skip((long)(page - 1) * limit);
        query.limit(limit);
        List<Sportsman> sportsmans = mongoTemplate.find(query, Sportsman.class);
        List<SportsmanDto> sportsmanDtos = sportsmans.stream()
            .map(sportsmanMapper::toDto)
            .toList();
        int totalPages = (int) Math.ceil((double) totalItems / limit);
        return new PagedResult<>(sportsmanDtos, page, limit, totalPages, (int) totalItems);
    }

    @Override
    public SportsmanDto save(SportsmanDto sportsmanDto) {
        Sportsman sportsman = sportsmanMapper.fromDto(sportsmanDto);
        Sportsman savedSportsman = mongoTemplate.save(sportsman);
        return sportsmanMapper.toDto(savedSportsman);
    }

    @Override
    public PagedResult<SportsmanDto> getAll(Integer page, Integer limit, String fullNamePrefix) {
        Query query = new Query();
        if (fullNamePrefix != null && !fullNamePrefix.isEmpty()) {
            query.addCriteria(Criteria.where("fullName").regex(fullNamePrefix, CASE_INSENSITIVE_REGEX));
        }
        long totalItems = mongoTemplate.count(query, Sportsman.class);
        query.skip((long)(page - 1) * limit);
        query.limit(limit);
        List<Sportsman> sportsmans = mongoTemplate.find(query, Sportsman.class);
        List<SportsmanDto> sportsmanDtos = sportsmans.stream()
            .map(sportsmanMapper::toDto)
            .toList();
        int totalPages = (int) Math.ceil((double) totalItems / limit);
        return new PagedResult<>(sportsmanDtos, page, limit, totalPages, (int) totalItems);
    }
}
