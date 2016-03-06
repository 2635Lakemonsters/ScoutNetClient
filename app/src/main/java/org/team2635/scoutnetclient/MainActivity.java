package org.team2635.scoutnetclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.team2635.scoutnetclient.dialogs.UploadPromptDialog;
import org.team2635.scoutnetclient.fragments.AssignmentsFragment;
import org.team2635.scoutnetclient.utilities.DataManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements UploadPromptDialog.NoticeDialogListener
{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nosaveactionmenu, menu);
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

    private void showSettingsActivity()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                showSettingsActivity();
                return true;
            case R.id.action_submit:
                showDialog("uploadPrompt");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
        for (final String s : urls)
        {
            Thread thread = new Thread(new Runnable()
            {
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
                        } finally
                        {
                            urlConnection.disconnect();
                        }

                    } catch (IOException e)
                    {
                        Log.e(TAG, e.toString());
                        MainActivity.this.runOnUiThread(new Runnable()
                                                        {
                                                            @Override
                                                            public void run()
                                                            {
                                                                showDialog("uploadFailure");
                                                            }
                                                        }

                        );
                        success = false;
                    } finally
                    {
                        if (success)
                        {
                            manager.clearData();
                            MainActivity.this.runOnUiThread(new Runnable()
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

    private void showDialog(String dialog)
    {
        switch (dialog)
        {
            case ("success"):
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                break;
            case ("uploadPrompt"):
                DialogFragment uploadPromptDialog = new UploadPromptDialog();
                uploadPromptDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
                break;
            case ("dataSaved"):
                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                break;
            case ("uploadFailure"):
                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.e(TAG, "Expected 'success' or 'uploadPrompt' for showDialog(), got " + dialog);
                break;
        }

    }
}
