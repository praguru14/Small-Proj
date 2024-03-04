    package com.news.app.Controller;

    import com.news.app.Services.CricketServices;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;

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
        @GetMapping("/")
        public ModelAndView url(){
            ModelAndView modelAndView = new ModelAndView("cricbuzz");
            return modelAndView;
        }

        @PostMapping("/submitUrl")
        @ResponseBody
        public ResponseEntity<String> submitUrl(@RequestBody String urlValue) {
            // Process the input value received from the client
            ResponseEntity<String> response = cricketServices.liveScores(urlValue);
            return response;
        }


    }
