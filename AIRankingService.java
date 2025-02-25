package com.search.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AIRankingService {

    public List<String> rankResults(List<String> results, String query) {
        return results.stream()
                .sorted((a, b) -> Integer.compare(b.split(query, -1).length, a.split(query, -1).length))
                .collect(Collectors.toList());
    }
}