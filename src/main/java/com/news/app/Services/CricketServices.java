package com.news.app.Services;

import com.news.app.Utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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



    public ResponseEntity<String> liveScores(@RequestParam String link) {
        try {
            System.out.println(link);
                StringBuilder sb = new StringBuilder();
                for( char a : link.toCharArray()){
                    if(Character.isDigit(a)){
                        sb.append(a);
                    }
                }
            System.out.println(sb);
           String matchID = sb.substring(0,5);
            String url = Utility.VERCEL + "score?id=" + matchID;
            System.out.println(url);
            String responseData = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(responseData);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Resource not found.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred while fetching data.\"}");
        }
    }
}
