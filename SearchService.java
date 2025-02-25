package com.search.service;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final RestHighLevelClient client;

    public SearchService(RestHighLevelClient client) {
        this.client = client;
    }

    public List<String> searchDocuments(String query) {
        List<String> results = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("documents");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", query));
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            searchResponse.getHits().forEach(hit -> results.add(hit.getSourceAsString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}