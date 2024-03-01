package com.news.app.Services;
import com.news.app.Model.DriverData;
import com.news.app.Model.F1Model;
import com.news.app.Repository.DriverRepo;
import com.news.app.Repository.F1Repo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
public class F1Services {

    @Autowired
    private F1Repo f1Repo;
    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private RestTemplate restTemplate;
    public void saveDriverData(DriverData driverData) {
        driverRepo.save(driverData);
    }

    public String fetchDataAndSave(String apiUrl) {
        String responseBody = restTemplate.getForObject(apiUrl, String.class);
        F1Model[] f1Sessions = restTemplate.getForObject(apiUrl, F1Model[].class);
        F1Model f1Model = new F1Model();
        Arrays.stream(f1Sessions).forEach(f1Repo::save);
        return responseBody;
    }
    @Transactional
public String fetchDriverandSave(String url){

        String res = restTemplate.getForObject(url, String.class);
        DriverData[] driverData = restTemplate.getForObject(url, DriverData[].class);
        Arrays.stream(driverData).forEach(this::saveDriverData);

        return res;
}
    public ResponseEntity<F1Model[]> getByCountry(String apiUrl) {
        try {
            ResponseEntity<F1Model[]> responseEntity = restTemplate.getForEntity(apiUrl, F1Model[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                F1Model[] f1Models = responseEntity.getBody();
                if (f1Models != null && f1Models.length > 0) {
                    return new ResponseEntity<>(f1Models, HttpStatus.OK);
                } else {
                    log.info("Response body is null");
                    log.info("{}", (Object[]) new F1Model[0]);
                    return new ResponseEntity<>(new F1Model[0], HttpStatus.BAD_GATEWAY);
                }
            } else {
                log.info("Non-successful status code received: {}", responseEntity.getStatusCodeValue());
                // Handle non-successful status code if needed
                return new ResponseEntity<>(new F1Model[0], responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            // Handle exception if needed
            return new ResponseEntity<>(new F1Model[0], HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<DriverData[]> getByName(String urlByName) {
        try {
            ResponseEntity<DriverData[]> responseEntity = restTemplate.getForEntity(urlByName, DriverData[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
              DriverData[] driverData = responseEntity.getBody();
                if (driverData != null && driverData.length > 0) {

                    return new ResponseEntity<>(driverData, HttpStatus.OK);
                } else {
                    log.info("Response body is null");
                    log.info("{}", (Object[]) new DriverData[0]);
                    return new ResponseEntity<>(new DriverData[0], HttpStatus.BAD_GATEWAY);
                }
            } else {
                log.info("Non-successful status code received: {}", responseEntity.getStatusCodeValue());
                // Handle non-successful status code if needed
                return new ResponseEntity<>(new DriverData[0], responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            // Handle exception if needed
            return new ResponseEntity<>(new DriverData[0], HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void insertDriverDataWithId(DriverData[] driverDataArray) {
        String sql = "INSERT INTO driver (broadcast_name, country_code, driver_number, first_name, full_name, headshot_url, last_name, meeting_key, name_acronym, session_key, team_colour, team_name, id) " +
                "VALUES (:broadcastName, :countryCode, :driverNumber, :firstName, :fullName, :headshotUrl, :lastName, :meetingKey, :nameAcronym, :sessionKey, :teamColour, :teamName, :id)";
        for (DriverData driverData : driverDataArray) {
            entityManager.createNativeQuery(sql)
                    .setParameter("broadcastName", driverData.getBroadcastName())
                    .setParameter("countryCode", driverData.getCountryCode())
                    .setParameter("driverNumber", driverData.getDriverNumber())
                    .setParameter("firstName", driverData.getFirstName())
                    .setParameter("fullName", driverData.getFullName())
                    .setParameter("headshotUrl", driverData.getHeadshotUrl())
                    .setParameter("lastName", driverData.getLastName())
                    .setParameter("meetingKey", driverData.getMeetingKey())
                    .setParameter("nameAcronym", driverData.getNameAcronym())
                    .setParameter("sessionKey", driverData.getSessionKey())
                    .setParameter("teamColour", driverData.getTeamColour())
                    .setParameter("teamName", driverData.getTeamName())
                    .setParameter("id", driverData.getId())
                    .executeUpdate();
        }
    }
}

