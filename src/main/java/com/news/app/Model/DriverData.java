package com.news.app.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import java.util.UUID;

@Getter
@Setter
//@Entity(name = "driver")
public class DriverData {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonProperty("broadcast_name")
    private String broadcastName;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("driver_number")
    private int driverNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("headshot_url")
    private String headshotUrl;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("meeting_key")
    private int meetingKey;

    @JsonProperty("name_acronym")
    private String nameAcronym;

    @JsonProperty("session_key")
    private int sessionKey;

    @JsonProperty("team_colour")
    private String teamColour;

    @JsonProperty("team_name")
    private String teamName;

    public DriverData() {
    }

    // Primary constructor
    public DriverData(UUID id, String broadcastName, String countryCode, int driverNumber, String firstName, String fullName, String headshotUrl, String lastName, int meetingKey, String nameAcronym, int sessionKey, String teamColour, String teamName) {
        this.id = id;
        this.broadcastName = broadcastName;
        this.countryCode = countryCode;
        this.driverNumber = driverNumber;
        this.firstName = firstName;
        this.fullName = fullName;
        this.headshotUrl = headshotUrl;
        this.lastName = lastName;
        this.meetingKey = meetingKey;
        this.nameAcronym = nameAcronym;
        this.sessionKey = sessionKey;
        this.teamColour = teamColour;
        this.teamName = teamName;
    }
}
