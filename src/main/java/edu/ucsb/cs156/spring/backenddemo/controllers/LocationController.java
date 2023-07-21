package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Location info")
@Slf4j
@RestController
@RequestMapping("/api/locations/get")

public class LocationController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService locationQueryService;

    @Operation(summary="Get location", description ="JSON return format documented here: https://nominatim.openstreetmap.org/search/{location}?format=json")
    @GetMapping("/get")
    public ResponseEntity<String> getLocation(
        @Parameter(name="location", example="Isla Vista") @RequestParam String location
    ) throws JsonProcessingException {
        log.info("getLocation: location={}",location);
        String result = locationQueryService.getJSON(location);
        return ResponseEntity.ok().body(result);
    }
}