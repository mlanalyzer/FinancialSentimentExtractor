package com.mlanalyzer.examples.sentiment;

public class Stock
{
    private String symbol;
    private String name;
    private String exchange;

    public Stock()
    {

    }

    Stock(String symbol, String name, String exchange)
    {
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getExchange()
    {
        return exchange;
    }

    public void setExchange(String exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;

        Stock stock = (Stock) o;

        if (!exchange.equals(stock.exchange)) return false;
        if (!name.equals(stock.name)) return false;
        if (!symbol.equals(stock.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = symbol.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + exchange.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", exchange='" + exchange + '\'' +
                '}';
    }
}
