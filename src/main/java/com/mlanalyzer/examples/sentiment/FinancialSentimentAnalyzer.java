package com.mlanalyzer.examples.sentiment;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FinancialSentimentAnalyzer
{
    private static final String API_KEY = PropertyHelper.getProperty("api_key");
    private ObjectMapper objectMapper;
    private HtmlToPlainText htmlToPlainText;

    public FinancialSentimentAnalyzer()
    {
        htmlToPlainText = new HtmlToPlainText();
        objectMapper = new ObjectMapper();
    }

    public static void main(String args[])
    {
        new FinancialSentimentAnalyzer().extract();
    }

    private void extract()
    {
        Map<Stock, Map<String, Integer>> stockSentiment = new HashMap<Stock, Map<String, Integer>>();
        Map<Stock, String> titles = new HashMap<Stock, String>();

        Integer count = null;
        Document rss = getRSSDocument(PropertyHelper.getProperty("news_url"));

        if (rss != null)
        {
            Elements items = rss.select("item");

            if (items != null)
            {
                for (int i = 0; i < items.size(); i++)
                {
                    String title = items.get(i).select("title").text();
                    String text = title + items.get(i).select("description").text();
                    String sentiment = getSentiment(text);

                    if (sentiment == null)
                    {
                        sentiment = "Neutral";
                    }

                    List<Stock> companies = getListedCompanies(text);

                    for (Stock stock : companies)
                    {
                        Map<String, Integer> sentimentMap = stockSentiment.get(stock);

                        if (sentimentMap == null)
                        {
                            sentimentMap = new HashMap<String, Integer>();
                            stockSentiment.put(stock, sentimentMap);
                        }

                        sentimentMap.put(sentiment, (count = sentimentMap.get(sentiment)) != null ? count + 1 : 1);

                        titles.put(stock, title);
                    }
                }
            }
        }

        print(stockSentiment, titles);
    }

    private void print(Map<Stock, Map<String, Integer>> stockSentiments, Map<Stock, String> titles)
    {
        for (Stock stock : stockSentiments.keySet())
        {
            System.out.println(titles.get(stock));
            System.out.println(stock + " --> " + stockSentiments.get(stock));
            System.out.println("\n-------------------------------------------------------------------------\n");
        }
    }

    private List<Stock> getListedCompanies(String text)
    {
        List<Stock> companies = null;

        HttpResponse<JsonNode> request = Unirest.get("https://nehac-ml-analyzer.p.mashape.com/stocks?text=" + text)
                .header("X-Mashape-Authorization", API_KEY)
                .asJson();

        if (request != null)
        {
            try
            {
                Stock[] stocks = objectMapper.readValue(request.getBody().toString(), Stock[].class);
                companies = Arrays.asList(stocks);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return companies;
    }

    private String getSentiment(String text)
    {
        String sentiment = null;

        HttpResponse<JsonNode> request = Unirest.get("https://nehac-ml-analyzer.p.mashape.com/sentiment?text=" + text)
                .header("X-Mashape-Authorization", API_KEY)
                .asJson();

        if (request != null)
        {
            try
            {
                sentiment = request.getBody().getObject().getString("value");
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        return sentiment;
    }

    private String getPlainText(Element element)
    {
        return Jsoup.parse(element.text()).text();
    }

    private Document getRSSDocument(String news_url)
    {
        Document document = null;

        try
        {
            document = Jsoup.parse(new URL(news_url), 100000);
            System.out.println("Loaded RSS blob !");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return document;
    }
}
