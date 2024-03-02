package com.news.app.Controller;

import com.news.app.Services.CricketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cricket")
public class CricbuzzAPIController {

    @Autowired
    CricketServices cricketServices;
    @GetMapping("/link")
    public ResponseEntity<String> getData(){
        return cricketServices.getApiData();
    }

    @GetMapping("/live")
    public ResponseEntity<String> liveScores(@RequestParam String id){
        return cricketServices.liveScores(id);
    }
}
