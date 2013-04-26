package com.mlanalyzer.examples.sentiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper
{
    private static Properties properties;

    static
    {
        properties = new Properties();
        String propertyFileLocn = System.getProperty("properties", "./analyzer.properties");

        try
        {
            properties.load(new FileInputStream(new File(propertyFileLocn)));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key)
    {
        return String.valueOf(properties.get(key));
    }
}
