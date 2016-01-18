package org.team2635.scoutnetclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.team2635.scoutnetclient.dialogs.SuccessDialog;
import org.team2635.scoutnetclient.dialogs.UploadPromptDialog;
import org.team2635.scoutnetclient.utilities.DataManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FieldInfoActivity extends AppCompatActivity implements UploadPromptDialog.NoticeDialogListener
{
    //TODO:Create field scouting page/design
    private String[] urls;
    private int pepes = 0;
    private int viewToGet = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fieldinfo);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TextView pepeText = (TextView) findViewById(R.id.pepeCountText);
        pepeText.setText("" + pepes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nouploadactionmenu, menu);
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

    private void submitData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);

        urls = manager.getURLArray();
        //TODO: Get page adress from settings
        //String adress = "http://192.168.1.109/pitform.php";

        for(final String s : urls)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    try
                    {
                        URL url = new URL("http://192.168.1.109/pitform.php?" + s);
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

    //TODO: Add saveData() functionality
    private void saveData()
    {
        showSuccessDialog();
    }

    private void showUploadPromptDialog() {
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

    private void showSuccessDialog()
    {
        // Create an instance of the dialog fragment and show it
        DialogFragment successDialog = new SuccessDialog();
        successDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void addPepe()
    {
        pepes++;
        updatePepes(true);
    }

    public void removePepe()
    {
        updatePepes(false);
        pepes--;
    }

    private void updatePepes(boolean state)
    {
        System.out.println(pepes);
        viewToGet = 0;

        switch(pepes)
        {
            case 1:
                System.out.println("Case 1");
                viewToGet = R.id.pepe1;
                break;
            case 2:
                System.out.println("Case 2");
                viewToGet = R.id.pepe2;
                break;
            case 3:
                System.out.println("Case 3");
                viewToGet = R.id.pepe3;
                break;
            case 4:
                System.out.println("Case 4");
                viewToGet = R.id.pepe4;
                break;
            case 5:
                System.out.println("Case 5");
                viewToGet = R.id.pepe5;
                break;
        }
        System.out.println(viewToGet);

        ImageView pepeView = (ImageView) findViewById(viewToGet);
        if(state)
        {
            pepeView.setVisibility(View.VISIBLE);
        }
        else if (!state)
        {
            pepeView.setVisibility(View.INVISIBLE);
        }

        TextView pepeText = (TextView) findViewById(R.id.pepeCountText);
        pepeText.setText("" + pepes);
    }

}
