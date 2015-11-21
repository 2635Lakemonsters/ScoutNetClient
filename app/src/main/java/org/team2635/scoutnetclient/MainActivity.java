package org.team2635.scoutnetclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import org.team2635.scoutnetclient.fragments.AssignmentsFragment;
import org.team2635.scoutnetclient.utilities.DataManager;

public class MainActivity extends AppCompatActivity {

    public String[] urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nouploadactionmenu, menu);
        return true;
    }

    public void showPitActivity(View view)
    {
        Intent intent = new Intent(this, PitInfoActivity.class);
        startActivity(intent);
    }

    public void showMatchActivity(View view)
    {
        Intent intent = new Intent(this, FieldInfoActivity.class);
        startActivity(intent);
    }

    public void showAssignment(View view)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AssignmentsFragment fragment = new AssignmentsFragment();
        fragmentTransaction.add(R.id.assignment_container, fragment);
        fragmentTransaction.commit();

    }

    public void showSettingsActivity()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showSettingsActivity();
                return true;
            case R.id.action_submit:
                submitData();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void submitData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);
        GoogleFormUploader uploader = new GoogleFormUploader("1954rZGc8hvXG4V8i3A_8a5t77kVQf2jI2oigtZjuktk");

        urls = manager.getURLArray();

        for(String s : urls)
        {
            System.out.println("Extracted and ran a URL! Data: " + s);
            //uploader.runURL("10.26.35.17", s);
        }

        //Clears saved data sets from memory. Prevents duplicate uploads.
        manager.clearData();
    }
}
