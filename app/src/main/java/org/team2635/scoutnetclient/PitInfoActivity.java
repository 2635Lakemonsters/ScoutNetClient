package org.team2635.scoutnetclient;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import org.team2635.scoutnetclient.dialogs.SuccessDialog;
import org.team2635.scoutnetclient.dialogs.UploadPromptDialog;
import org.team2635.scoutnetclient.fragments.DefensesFragment;
import org.team2635.scoutnetclient.utilities.DataManager;
import org.team2635.scoutnetclient.utilities.PitPagerAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PitInfoActivity extends AppCompatActivity implements UploadPromptDialog.NoticeDialogListener
{

    private String[] urls;
    private ViewPager viewpager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitinfo);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewpager = (ViewPager) findViewById(R.id.pitPager);
        PitPagerAdapter padapter = new PitPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

        /**
        Intent intent = getIntent();
        String teamNumber = intent.getStringExtra("TEAMNUMBER");
        final EditText field = (EditText) findViewById(R.id.teamNumber);
        if (!teamNumber.equals(null))
        {
            field.setText(teamNumber);
        }
         **/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_save:
                saveData();
                return true;

            case R.id.action_submit:
                showUploadPromptDialog();
                //submitData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void showUploadPromptDialog()
    {
        // Create an instance of the dialog fragment and show it
        DialogFragment uploadPromptDialog = new UploadPromptDialog();
        uploadPromptDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment uploadPromptDialog) {
        submitData();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment uploadPromptDialog) {
        // User touched the dialog's negative button

    }

    private void saveData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);

        DefensesFragment defensesFrag = (DefensesFragment)  getSupportFragmentManager().findFragmentById(R.id.pitPager);
        String[] selections = defensesFrag.getSelections();

        //TODO: Commit selections to entries
        for(String s : selections)
        {
            //uploader.addEntry("");
        }

        EditText v_teamName = (EditText) findViewById(R.id.teamName);
        EditText v_teamNumber = (EditText) findViewById(R.id.teamNumber);
        EditText v_robotName = (EditText) findViewById(R.id.robotName);

        String teamName = v_teamName.getText().toString();
        String teamNumber = v_teamNumber.getText().toString();
        String robotName = v_robotName.getText().toString();

        v_robotName.setText("");
        v_teamName.setText("");
        v_teamNumber.setText("");

        GoogleFormUploader uploader = new GoogleFormUploader("1954rZGc8hvXG4V8i3A_8a5t77kVQf2jI2oigtZjuktk");
        uploader.addEntry("NAME", robotName);
        //uploader.upload();
        manager.write(uploader.getUrlData());
        showSuccessDialog();
    }
    private void submitData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);

        urls = manager.getURLArray();
        //TODO: Test address retrieval from settings
        final String address = sharedPref.getString("pref_key_server_ip", "");
        final String pageID = sharedPref.getString("pref_key_server_data_page", "");


        for(final String s : urls)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    try
                    {
                        URL url = new URL("http://" + address + "/" + pageID + "?" + s);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                        try
                        {
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            System.out.println(in);
                        }
                        finally
                        {
                            urlConnection.disconnect();
                        }
                    }

                    catch(MalformedURLException e)
                    {
                        e.printStackTrace();
                    }

                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            System.out.println("Extracted and ran a URL! Data: " + s);
        }

        //Clears saved data sets from memory. Prevents duplicate uploads.
        manager.clearData();
        showSuccessDialog();
    }

    private void showSuccessDialog()
    {
        // Create an instance of the dialog fragment and show it
        DialogFragment successDialog = new SuccessDialog();
        successDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }
}
