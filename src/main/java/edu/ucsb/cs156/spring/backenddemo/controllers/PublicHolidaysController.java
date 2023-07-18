package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="Public Holiday Info from https://date.nager.at/Api")
@Slf4j
@RestController
@RequestMapping("/api/PublicHolidays")
public class PublicHolidaysController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService PublicHolidayQueryService;

    @Operation(summary = "Get public holidays for a given year and country")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="countryCode", description="2 letter country code, e.g. US, MX, CN") @RequestParam String countryCode
        @Parameter(name="year", description="year, e.g. 2012") @RequestParam String year,
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: countryCode={} year={}", countryCode, year);
        String result = PublicHolidayQueryService.getJSON(countryCode, year);
        return ResponseEntity.ok().body(result);
    }

}
