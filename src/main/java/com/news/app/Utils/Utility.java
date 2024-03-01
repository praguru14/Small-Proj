package com.news.app.Utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Utility {
    public static final String URL = "https://api.openf1.org/v1/sessions";
    public static final String DRIVER_NAME =  "https://api.openf1.org/v1/drivers?last_name=";
    public static final String DRIVER ="https://api.openf1.org/v1/drivers" ;
}
