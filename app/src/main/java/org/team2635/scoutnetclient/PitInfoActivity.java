package org.team2635.scoutnetclient;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;
import org.team2635.scoutnetclient.dialogs.UploadPromptDialog;
import org.team2635.scoutnetclient.fragments.DefensesFragment;
import org.team2635.scoutnetclient.fragments.RobotInfoFragment;
import org.team2635.scoutnetclient.fragments.StrategyInfoFragment;
import org.team2635.scoutnetclient.fragments.TeamInfoFragment;
import org.team2635.scoutnetclient.utilities.DataManager;
import org.team2635.scoutnetclient.utilities.PitPagerAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PitInfoActivity extends AppCompatActivity implements UploadPromptDialog.NoticeDialogListener
{
    private static final String TAG = "PitInfoActivity";
    PitPagerAdapter padapter = new PitPagerAdapter(getSupportFragmentManager());
    ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitinfo);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pitPager);
        viewPager.setAdapter(padapter);

        //TODO: Implement this
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
                showDialog("uploadPrompt");
                //submitData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //TODO: Test fragment titles on action bar
    public void setActionBarTitle(String title) {
        getSupportActionBar().setSubtitle(title);
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment uploadPromptDialog)
    {
        submitData();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment uploadPromptDialog)
    {
        // User touched the dialog's negative button
    }

    //TODO: Convert data saving to json object
    private void saveData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);
        JSONObject jsonObject = new JSONObject();

        //TODO: Fix getting of scout name
        final String scoutName = sharedPref.getString("pref_key_scout_name", "");

        //2 Fields
        try
        {
            jsonObject.accumulate("DATATYPE", "pitData");
            jsonObject.accumulate("SCOUTNAME", scoutName);
        }
        catch(Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        //Get references to fragments
        TeamInfoFragment teamFrag = (TeamInfoFragment) padapter.getItem(0);
        RobotInfoFragment robotFrag = (RobotInfoFragment) padapter.getItem(1);
        StrategyInfoFragment strategyFrag = (StrategyInfoFragment) padapter.getItem(2);
        DefensesFragment defensesFrag = (DefensesFragment)  padapter.getItem(3);



        //Set page to teaminfo fragment
        viewPager.setCurrentItem(0, false);

        //Get data from teaminfo fragment; 3 fields
        String teamNumber = teamFrag.getTeamNumber();
        String teamName = teamFrag.getTeamName();
        String robotName = teamFrag.getRobotName();

        try
        {
            jsonObject.accumulate("TEAMNUMBER", teamNumber);
            jsonObject.accumulate("TEAMNAME", teamName);
            jsonObject.accumulate("ROBOTNAME", robotName);

        }
        catch(Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }



        //Set page to robot info
        viewPager.setCurrentItem(1, false);

        //Get data from robot info
        String wheels = robotFrag.getNumberOfWheels();
        String locomotion = robotFrag.getLocomotionType();
        String vision = robotFrag.getUsingVision();
        String visionUsage = robotFrag.getVisionUsage();
        String driveTrain = robotFrag.getDriveTrain();
        String auto = robotFrag.getUsingAuto();
        String autoUsage = robotFrag.getAutoUsage();

        try
        {
            jsonObject.accumulate("NUMOFWHEELS", wheels);
            jsonObject.accumulate("LOCOMOION", locomotion);
            jsonObject.accumulate("VISION", vision);
            jsonObject.accumulate("VISIONUSAGE", visionUsage);
            jsonObject.accumulate("DRIVETRAIN", driveTrain);
            jsonObject.accumulate("AUTO", auto);
            jsonObject.accumulate("AUTOUSAGE", autoUsage);
        }
        catch(Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        String[] robotSelections = robotFrag.getCheckBoxData();
        String[] robotOptions = robotFrag.getOptions();

        //TODO: Test this
        int i = 0;
        for(String s : robotSelections)
        {
            try
            {
                jsonObject.accumulate(robotOptions[i], s);
            }
            catch(Exception e)
            {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            ++i;
        }



        //Set view to strategy info fragment
        viewPager.setCurrentItem(2, false);

        //Get data from strategy info fragment
        String[] strategySelections = strategyFrag.getData();
        String[] strategyOptions = strategyFrag.getOptions();

        //TODO: Test this
        i = 0;
        for(String s : strategySelections)
        {
            try
            {
                jsonObject.accumulate(strategyOptions[i], s);
            }
            catch(Exception e)
            {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            ++i;
        }



        //Set page to defenses fragment
        viewPager.setCurrentItem(3, false);

        //Get data from defenses selection fragment
        String[] defenseSelections = defensesFrag.getSelections();
        String[] defenseOptions = defensesFrag.getDefenses();

        //TODO: Test this
        i = 0;
        for(String s : defenseSelections)
        {
            try
            {
                jsonObject.accumulate(defenseOptions[i], s);
            }
            catch(Exception e)
            {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            ++i;
        }



        manager.write(jsonObject.toString());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        showDialog("dataSaved");
    }

    private void submitData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final DataManager manager = new DataManager(sharedPref);

        String[] urls = manager.getURLArray();
        //TODO: Test address retrieval from settings
        final String address = sharedPref.getString("pref_key_server_ip", "");
        final String pageID = sharedPref.getString("pref_key_server_data_page", "");

        //TODO: Test new data post functionality
        for(final String s : urls)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    boolean success = true;
                    try
                    {

                        URL url = new URL("http://" + address + "/" + pageID + "?" + s);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setRequestProperty("Content-Type", "application/json");
                        urlConnection.connect();

                        OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                        out.write(s);
                        out.close();

                        try
                        {
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            Log.d(TAG, in.toString());
                        }
                        finally
                        {
                            urlConnection.disconnect();
                        }

                    }
                    catch(IOException e)
                    {
                        Log.e(TAG, e.toString());
                        PitInfoActivity.this.runOnUiThread(new Runnable()
                                                        {
                                                            @Override
                                                            public void run()
                                                            {
                                                                showDialog("uploadFailure");
                                                            }
                                                        }

                        );
                        success = false;
                    }
                    finally
                    {
                        if(success)
                        {
                            manager.clearData();
                            PitInfoActivity.this.runOnUiThread(new Runnable()
                                                            {
                                                                @Override
                                                                public void run()
                                                                {
                                                                    showDialog("success");
                                                                }
                                                            }

                            );
                        }
                    }
                }
            });
            thread.start();

            Log.d(TAG, "Extracted and ran a URL! Data: " + s);
        }

        //Clears saved data sets from memory. Prevents duplicate uploads.
        //TODO: Implement checking from server


    }

    private void showDialog(String dialog)
    {
        switch(dialog)
        {
            case("success"):
                //DialogFragment successDialog = new SuccessDialog();
                //successDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");

                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                break;
            case("uploadPrompt"):
                DialogFragment uploadPromptDialog = new UploadPromptDialog();
                uploadPromptDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
                break;
            case("dataSaved"):
                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                break;
            case("uploadFailure"):
                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.e(TAG, "Expected 'success' or 'uploadPrompt' for showDialog(), got " + dialog);
                break;
        }

    }
}
