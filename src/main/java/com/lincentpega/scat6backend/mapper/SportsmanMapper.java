package com.lincentpega.scat6backend.mapper;

import com.lincentpega.scat6backend.dto.SportsmanDto;
import com.lincentpega.scat6backend.models.Sportsman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SportsmanMapper {

    SportsmanDto toDto(Sportsman sportsman);

    Sportsman fromDto(SportsmanDto sportsmanDto);
} 