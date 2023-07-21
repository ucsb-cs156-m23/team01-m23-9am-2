package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Zipcode")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService ZipCodeQueryService;

    @Operation(summary = "Get information about a us zipcode", description = "Zip Code Information from http://www.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCode(
        @Parameter(name="ZipCode", description="US zipcode, e.g. 93106") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCode: zipcode={}", zipcode);
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}
