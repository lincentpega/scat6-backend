package com.lincentpega.scat6backend.service;

import com.lincentpega.scat6backend.dto.PagedResult;
import com.lincentpega.scat6backend.dto.SportsmanDto;

import java.util.Optional;

public interface SportsmanService {

    PagedResult<SportsmanDto> getAll(Integer page, Integer limit, String fullNamePrefix);

    Optional<SportsmanDto> findById(String id);

    PagedResult<SportsmanDto> searchByFullName(String fullNamePart, Integer page, Integer limit);

    SportsmanDto save(SportsmanDto sportsmanDto);
}
