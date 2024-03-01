        package com.news.app.Model;

        import com.fasterxml.jackson.annotation.JsonProperty;
        import jakarta.persistence.Entity;
        import jakarta.persistence.GeneratedValue;
        import jakarta.persistence.GenerationType;
        import jakarta.persistence.Id;
        import lombok.Data;
        import org.springframework.stereotype.Component;

        import java.util.Date;
        import java.util.UUID;

        @Data
        @Component
        @Entity(name = "session")
        public class F1Model {

                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                private UUID id;

                @JsonProperty("location")      
                private String location;


                @JsonProperty("country_key")
                private int countryKey;

                @JsonProperty("country_code")
                private String countryCode;

                @JsonProperty("country_name")
                private String countryName;

                @JsonProperty("circuit_key")
                private int circuitKey;

                @JsonProperty("circuit_short_name")
                private String circuitShortName;

                @JsonProperty("session_type")
                private String sessionType;

                @JsonProperty("session_name")
                private String sessionName;

                @JsonProperty("date_start")
                private Date dateStart;

                @JsonProperty("date_end")
                private Date dateEnd;

                @JsonProperty("gmt_offset")
                private String gmtOffset;

                @JsonProperty("session_key")
                private int sessionKey;

                @JsonProperty("meeting_key")
                private int meetingKey;

                @JsonProperty("year")
                private int year;

        }
