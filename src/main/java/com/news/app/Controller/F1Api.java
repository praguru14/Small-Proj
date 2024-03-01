package com.news.app.Controller;
import com.news.app.Model.DriverData;
import com.news.app.Model.F1Model;
import com.news.app.Repository.DriverRepo;
import com.news.app.Services.F1Services;
import com.news.app.Utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class F1Api{

    @Autowired
    private F1Services f1Service;
    @Autowired
    private DriverRepo driverRepo;

    @GetMapping("/user")
    public String fetchDataAndSave() {
        String apiUrl = "https://api.openf1.org/v1/sessions";
        return f1Service.fetchDataAndSave(apiUrl);
    }

    @GetMapping("/byCountry")
    public ResponseEntity<F1Model[]> getByCountry(@RequestParam String country_name) {
        String apiUrlWithCountry = "https://api.openf1.org/v1/sessions?country_name=" + country_name;
        return f1Service.getByCountry(apiUrlWithCountry);
    }
    @GetMapping("/driver")
    public String fetchDriverandSave() {
        return f1Service.fetchDriverandSave(Utility.DRIVER);
    }
    @GetMapping("/byName")
    public ResponseEntity<DriverData[]> getByName(@RequestParam String last_name){
        String urlByName = Utility.DRIVER_NAME + last_name;
        return f1Service.getByName(urlByName);
    }

    @GetMapping("/all")
    List<DriverData> getAll(){
        return driverRepo.findAll();
    }

}

