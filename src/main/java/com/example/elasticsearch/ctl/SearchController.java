package com.example.elasticsearch.ctl;

import com.example.elasticsearch.svc.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public String getAssetsList() throws IOException {
        searchService.searchAssetsList();

//        String base = "dsp_*_*_";
//        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
//        StringBuilder endpoints = new StringBuilder("/");
//
//        int optionDate = 7;
//        for(int i=0; i<optionDate+1; ++i) {
//            String endpoint = base + localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
//            endpoint = URLEncoder.encode(endpoint, "UTF-8");
//            endpoints.append(endpoint + ",");
//
//            localDateTime = localDateTime.minus(1, ChronoUnit.DAYS);
//        }
//        endpoints.deleteCharAt(endpoints.length() - 1);
//
//        log.info("{}", endpoints);

        return "OK";
    }
}
