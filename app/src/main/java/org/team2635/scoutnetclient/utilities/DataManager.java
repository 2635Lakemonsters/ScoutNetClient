package org.team2635.scoutnetclient.utilities;

import android.content.SharedPreferences;
import android.util.Log;

public class DataManager
{
    private final SharedPreferences m_sharedPref;
    private final String TAG = "DataManager";
    private int urlCount = 0;

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
            editor.apply();
        }
    }


    public void write(String url)
    {
        SharedPreferences.Editor editor = m_sharedPref.edit();
        ++urlCount;
        editor.putString("url" + urlCount, url);

        editor.putInt("count", urlCount);

        editor.apply();

        Log.d(TAG, "Url data logged to memory! Data: " + url);
        Log.d(TAG, "Url count: " + urlCount);
        Log.d(TAG, "Stored URL: " + m_sharedPref.getInt("count", 999));
    }

    public String[] getURLArray()
    {
        String[] urls = new String[urlCount + 1];
        for(int i=0; i<urlCount + 1; i++)
        {
            String url = m_sharedPref.getString("url"+i, "");
            urls[i] = url;
            Log.d(TAG, "Data assigned to array! Place in array " + i + ". Data: " + url);
        }
        return urls;
    }

    public int getURLCount()
    {
        return urlCount;
    }

    //TODO:Test proper data clearing functionality
    public void clearData()
    {
        SharedPreferences.Editor editor = m_sharedPref.edit();
        urlCount = m_sharedPref.getInt("count", 999);

        for(int i=1; i<urlCount; ++i)
        {
            editor.remove("url"+i);
        }

        editor.remove("count");
        editor.apply();
    }
}
