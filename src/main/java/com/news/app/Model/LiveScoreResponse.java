package com.news.app.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LiveScoreResponse {
    private String title;
    private String update;
    private String process;
    private String noResult;
    private String stumps;
    private String lunch;
    private String inningsBreak;
    private String tea;
    private String rainBreak;
    private String wetOutfield;
    private String matchDate;
    private String liveScore;
    private String runRate;
    private String batterOne;
    private String batterTwo;
    private String batterOneRun;
    private String batterTwoRun;
    private String batterOneBall;
    private String batterTwoBall;
    private String batterOneSR;
    private String batterTwoSR;
    private String bowlerOne;
    private String bowlerTwo;
    private String bowlerOneOver;
    private String bowlerTwoOver;
    private String bowlerOneRun;
    private String bowlerTwoRun;
    private String bowlerOneEco;
    private String bowlerTwoEco;
    private String bowlerOneWicket;
    private String bowlerTwoWicket;

    public LiveScoreResponse(String title, String update, String process, String noResult, String stumps, String lunch, String inningsBreak, String tea, String rainBreak, String wetOutfield, String matchDate, String liveScore, String runRate, String batterOne, String batterTwo, String batterOneRun, String batterTwoRun, String batterOneBall, String batterTwoBall, String batterOneSR, String batterTwoSR, String bowlerOne, String bowlerTwo, String bowlerOneOver, String bowlerTwoOver, String bowlerOneRun, String bowlerTwoRun, String bowlerOneEco, String bowlerTwoEco, String bowlerOneWicket, String bowlerTwoWicket) {
    }
}