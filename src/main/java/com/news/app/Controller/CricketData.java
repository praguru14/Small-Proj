package com.news.app.Controller;
import com.news.app.Model.LiveScoreResponse;
import com.news.app.Model.ScoreResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CricketData {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://www.cricbuzz.com/live-cricket-scores/";

    @GetMapping("/score/live")
    public ResponseEntity<?> getLiveScore(@RequestParam("id") String id) throws IOException {
        String url = baseUrl + id;
        System.out.println(url);
        String htmlContent = restTemplate.getForObject(url, String.class);
        Document document = Jsoup.parse(htmlContent);
        System.out.println(htmlContent);
        Document document1 = Jsoup.parse(htmlContent);


        String title = getTitle(document);
        String update = getTextFromElement(document, ".cb-col-100.cb-min-stts.cb-text-complete");
        String process = getTextFromElement(document, ".cb-text-inprogress");
        String noResult = getTextFromElement(document, ".cb-col.cb-col-100.cb-font-18.cb-toss-sts.cb-text-abandon");
        String stumps = getTextFromElement(document, ".cb-text-stumps");
        String lunch = getTextFromElement(document, ".cb-text-lunch");
        String inningsBreak = getTextFromElement(document, ".cb-text-inningsbreak");
        String tea = getTextFromElement(document, ".cb-text-tea");
        String rainBreak = getTextFromElement(document, ".cb-text-rain");
        String wetOutfield = getTextFromElement(document, ".cb-text-wetoutfield");
        String matchDate = getMatchDate(document);
        String liveScore = getTextFromElement(document, ".cb-font-20.text-bold");
        String runRate = getTextFromElement(document, ".cb-font-12.cb-text-gray");
        String batterOne = getTextFromElement(document, ".cb-col.cb-col-50", 1);
        String batterTwo = getTextFromElement(document, ".cb-col.cb-col-50", 2);
        String batterOneRun = getTextFromElement(document, ".cb-col.cb-col-10.ab.text-right", 0);
        String batterTwoRun = getTextFromElement(document, ".cb-col.cb-col-10.ab.text-right", 2);
        String batterOneBall = getTextFromElement(document, ".cb-col.cb-col-10.ab.text-right", 1);
        String batterTwoBall = getTextFromElement(document, ".cb-col.cb-col-10.ab.text-right", 3);
        String batterOneSR = getTextFromElement(document, ".cb-col.cb-col-14.ab.text-right", 0);
        String batterTwoSR = getTextFromElement(document, ".cb-col.cb-col-14.ab.text-right", 1);
        String bowlerOne = getTextFromElement(document, ".cb-col.cb-col-50", 4);
        String bowlerTwo = getTextFromElement(document, ".cb-col.cb-col-50", 5);
        String bowlerOneOver = getTextFromElement(document, ".cb-col.cb-col-10.text-right", 4);
        String bowlerTwoOver = getTextFromElement(document, ".cb-col.cb-col-10.text-right", 6);
        String bowlerOneRun = getTextFromElement(document, ".cb-col.cb-col-10.text-right", 5);
        String bowlerTwoRun = getTextFromElement(document, ".cb-col.cb-col-10.text-right", 7);
        String bowlerOneEco = getTextFromElement(document, ".cb-col.cb-col-14.text-right", 2);
        String bowlerTwoEco = getTextFromElement(document, ".cb-col.cb-col-14.text-right", 3);
        String bowlerOneWicket = getTextFromElement(document, ".cb-col.cb-col-8.text-right", 5);
        String bowlerTwoWicket = getTextFromElement(document, ".cb-col.cb-col-8.text-right", 7);

        return ResponseEntity.ok().body(new LiveScoreResponse(
                title, update, process, noResult, stumps, lunch, inningsBreak, tea, rainBreak, wetOutfield,
                matchDate, liveScore, runRate, batterOne, batterTwo, batterOneRun, batterTwoRun, batterOneBall,
                batterTwoBall, batterOneSR, batterTwoSR, bowlerOne, bowlerTwo, bowlerOneOver, bowlerTwoOver,
                bowlerOneRun, bowlerTwoRun, bowlerOneEco, bowlerTwoEco, bowlerOneWicket, bowlerTwoWicket
        ));
    }

    private String getTextFromElement(Document document, String selector) {
        Element element = document.selectFirst(selector);
        return element != null ? element.text().trim() : "Data Not Found";
    }

    private String getTextFromElement(Document document, String selector, int index) {
        Elements elements = document.select(selector);
        return elements.size() > index ? elements.get(index).text().trim() : "Data Not Found";
    }

    private String getMatchDate(Document document) {
        Element matchDateElement = document.selectFirst("span[itemprop=startDate]");
        if (matchDateElement != null) {
            String matchTime = matchDateElement.attr("content").split("\\+")[0];
            // Implement date formatting as needed
            return "Match Date: " + matchTime;
        } else {
            return "Match Date: Data Not Found";
        }
    }


    private String getTitle(Document document) {
        Element titleElement = document.selectFirst(".cb-nav-hdr.cb-font-18.line-ht24");
        return titleElement != null ? titleElement.text().replace(", Commentary", "").trim() : "Title Not Found";
    }




    @GetMapping("/score")
    public ScoreResponse getScore(@RequestParam String id) {
        String url = "https://www.cricbuzz.com/live-cricket-scores/" + id;
        String htmlContent = fetchHtmlContent(url);

        // Extract data using regular expressions
        String title = extractData(htmlContent, "<h1 class=\"cb-nav-hdr cb-font-18 line-ht24\">(.*?), Commentary</h1>");
        String update = extractData(htmlContent, "<div class=\"cb-col cb-col-100 cb-min-stts cb-text-complete\">(.*?)</div>");
        String liveScore = extractData(htmlContent, "<span class=\"cb-font-20 text-bold\">(.*?)</span>");

        return new ScoreResponse(title, update, liveScore);
    }

    private String fetchHtmlContent(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    private String extractData(String htmlContent, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Data not found";
        }
    }




    }
