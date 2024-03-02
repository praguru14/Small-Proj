package com.news.app.Model;

import lombok.Data;

@Data
public class ScoreResponse {
    private String title;
    private String update;
    private String liveScore;

    public ScoreResponse(String title, String update, String liveScore) {
        this.title = title;
        this.update = update;
        this.liveScore = liveScore;
    }

}