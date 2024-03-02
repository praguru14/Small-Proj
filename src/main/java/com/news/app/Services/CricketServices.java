package com.news.app.Services;

import com.news.app.Utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CricketServices {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> getApiData() {
        try {
            // Simulate fetching data from a third-party API
            String data = Utility.VERCEL;
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);
            return ResponseEntity.ok(jsonData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred while fetching data.\"}");
        }
    }



    public ResponseEntity<String> liveScores(@RequestParam String id) {
        try {
            String url = Utility.VERCEL + "score?id=" + id;
            String responseData = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(responseData);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Resource not found.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred while fetching data.\"}");
        }
    }
}
