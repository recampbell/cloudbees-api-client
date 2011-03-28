/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static Date parseW3CDate(String dateString) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",
                Locale.US);
        int tzMinuteIndex = dateString.length()-3;
        if(dateString.charAt(tzMinuteIndex) == ':') //strip ':' from timezone since sdf can't handle it
        {
            dateString = dateString.substring(0, tzMinuteIndex) + dateString.substring(tzMinuteIndex+1);
        }
        
        Date d = sdf.parse(dateString);
        return d;
    }
    
    public static Date parseRssDate(String dateString) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.US);
        Date d = sdf.parse(dateString);
        return d;
    }
    
    public static Date parseW3CDateWithFractionalSeconds(String dateString) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.US);
        
        Date d = sdf.parse(dateString);
        return d;
    }
    
    public static String toW3CDateString(Date d)
    {
        //SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy'T'");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",
                Locale.US);
        String dateString = sdf.format(d);
        
        //insert the : char into the timezone to make it truly W3C
        int tzMinuteIndex = dateString.length()-2;
        dateString = dateString.substring(0, tzMinuteIndex) + ":" + dateString.substring(tzMinuteIndex);
        return dateString;
    }
    
    public static Date parseW3CDateRobust(String dateString) throws ParseException
    {
        try{
            return parseW3CDate(dateString);
        }
        catch(ParseException e)
        {
            return parseW3CDateWithFractionalSeconds(dateString);
        }
    }
}
