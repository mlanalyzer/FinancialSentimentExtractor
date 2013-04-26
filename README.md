FinancialSentimentExtractor
===========================

Extract Stock Symbols and their sentiment from RSS feeds

For this example we are going to analyze the RSS stock feed from NASDAQ.

This example uses the stock extraction and sentiment extraction from [ml-analyzer](https://www.mashape.com/mlanalyzer/ml-analyzer#!documentation).
This also uses the Unirest api from [mashape](https://www.mashape.com/)

Specify the subscription key from mashape in analyzer.properties

Usage : java -Dproperties=analyzer.properties -cp target/FinancialSentimentExtractor-1.0-SNAPSHOT-jar-with-dependencies.jar com.mlanalyzer.examples.sentiment.FinancialSentimentAnalyzer
