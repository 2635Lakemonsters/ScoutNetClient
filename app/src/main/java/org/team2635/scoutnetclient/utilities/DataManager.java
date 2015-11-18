package org.team2635.scoutnetclient.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Siren on 11/1/2015.
 */
public class DataManager
{
    public SharedPreferences m_sharedPref;
    public int urlCount = 0;
    public String[] urls;

    public DataManager(SharedPreferences sharedPref)
    {
       m_sharedPref = sharedPref;
        SharedPreferences.Editor editor = m_sharedPref.edit();

        urlCount = m_sharedPref.getInt("count", 999);

        if(urlCount == 999)
        {
            //First Instantiation, setting value to zero. Because compatibility and crap
            urlCount = 0;
            editor.putInt("count", urlCount);
            editor.commit();
        }
    }


    public void write(String url)
    {
        SharedPreferences.Editor editor = m_sharedPref.edit();
        ++urlCount;
        editor.putString("url" + urlCount, url);

        editor.putInt("count", urlCount);

        editor.commit();

        System.out.println("Url data logged to memory! Data: " + url);
        System.out.println("Url count: " + urlCount);
        System.out.println("Stored URL: " + m_sharedPref.getInt("count", 999));
    }

    public String[] getURLArray()
    {
        urls = new String[urlCount + 1];
        for(int i=0; i<urlCount + 1; i++)
        {
            String url = m_sharedPref.getString("url"+i, "No Data");
            urls[i] = url;
            System.out.println("Data assigned to array! Place in array " + i + ". Data: " + url);
        }
        return urls;
    }

    public int getURLCount()
    {
        return urlCount;
    }

    public void clearData()
    {
        SharedPreferences.Editor editor = m_sharedPref.edit();

        editor.clear();

        editor.commit();
    }
}
