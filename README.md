FinancialSentimentExtractor
===========================

Extract Stock Symbols and their sentiment from RSS feeds

For this example we are going to analyze the RSS stock feed from NASDAQ.

This example uses the stock extraction and sentiment extraction from [ml-analyzer](https://www.mashape.com/mlanalyzer/ml-analyzer#!documentation).
This also uses the Unirest api from [mashape](https://www.mashape.com/)

Specify the subscription key from mashape in analyzer.properties

Usage : java -Dproperties=analyzer.properties -cp target/FinancialSentimentExtractor-1.0-SNAPSHOT-jar-with-dependencies.jar com.mlanalyzer.examples.sentiment.FinancialSentimentAnalyzer

Sample Output
-------------

According to IMF Russia expands its gold holdings  
Stock{symbol='IMF', name='Western Asset Inflation Fund Inc', exchange='NYSE'} --> {Positive=1}  

-------------------------------------------------------------------------

ExxonMobil: Earnings Rise, Sales Fall - Analyst Blog  
Stock{symbol='XOM', name='Exxon Mobil Corp.', exchange='NYSE'} --> {Positive=1}  

-------------------------------------------------------------------------

Raymond James Lags on Earnings - Analyst Blog  
Stock{symbol='RJF', name='Raymond James Financial', exchange='NYSE'} --> {Positive=1}  

-------------------------------------------------------------------------

Dow's Earnings Tops on Agriculture Boost - Analyst Blog  
Stock{symbol='DOW', name='DOW Chemical Company', exchange='NYSE'} --> {Positive=1}  

-------------------------------------------------------------------------

Earnings Beat for Time Warner Cable - Analyst Blog  
Stock{symbol='TWC', name='Time Warner Cable Inc', exchange='NYSE'} --> {Positive=1}  

-------------------------------------------------------------------------
