package com.search.controller;

import org.springframework.web.bind.annotation.*;
import com.search.service.SearchService;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<String> search(@RequestParam String query) {
        return searchService.searchDocuments(query);
    }
}