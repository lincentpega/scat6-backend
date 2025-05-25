package com.lincentpega.scat6backend.dto;

import java.util.List;

public record PagedResult<T>(
    List<T> items,
    Integer page,
    Integer limit,
    Integer totalPages,
    Integer totalItems
) {
} 