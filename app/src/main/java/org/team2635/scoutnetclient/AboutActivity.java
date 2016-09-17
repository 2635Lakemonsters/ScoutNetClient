package org.team2635.scoutnetclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.team2635.scoutnetclient.utilities.DataManager;

/**
 * Created by Siren on 9/13/2016.
 */
public class AboutActivity extends AppCompatActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void mainScreen(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clear(View view)
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);

        boolean success = manager.clearData();
        if(success)
        {
            Toast.makeText(getApplicationContext(), "Data Cleared", Toast.LENGTH_SHORT).show();
        }
    }
}
