package com.lincentpega.scat6backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lincentpega.scat6backend.service.SportsmanService;
import com.lincentpega.scat6backend.dto.PagedResult;
import com.lincentpega.scat6backend.dto.SportsmanDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/sportsmans")
@RequiredArgsConstructor
public class SportsmanController {

    private final SportsmanService sportsmanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SportsmanDto> getSportsman(@PathVariable String id) {
        return sportsmanRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @PostMapping
    public ResponseEntity<SportsmanDto> createSportsman(@RequestBody SportsmanDto sportsmanDto) {
        SportsmanDto savedSportsman = sportsmanRepository.save(sportsmanDto);
        return ResponseEntity.ok(savedSportsman);
    }

    @GetMapping
    public ResponseEntity<PagedResult<SportsmanDto>> getSportsmans(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam(required = false) String fullNamePrefix) {
        PagedResult<SportsmanDto> result = sportsmanRepository.getAll(page, limit, fullNamePrefix);
        return ResponseEntity.ok(result);
    }
}
