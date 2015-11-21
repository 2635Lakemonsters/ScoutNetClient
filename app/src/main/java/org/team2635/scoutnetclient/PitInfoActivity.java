package org.team2635.scoutnetclient;

import android.content.Context;
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

import org.team2635.scoutnetclient.dialogs.UploadPromptDialog;
import org.team2635.scoutnetclient.utilities.DataManager;
import org.team2635.scoutnetclient.utilities.PagerAdapter;

import static android.app.PendingIntent.getActivity;


public class PitInfoActivity extends AppCompatActivity  implements UploadPromptDialog.NoticeDialogListener
{

    public String[] urls;
    ViewPager viewpager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitinfo);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewpager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
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
                return true;

            case R.id.action_save:
                saveData();
                return true;

            case R.id.action_submit:
                //showUploadPromptDialog();
                submitData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void showUploadPromptDialog() {
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

    public void saveData()
    {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DataManager manager = new DataManager(sharedPref);

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
        uploader.addEntry("986342234", "Yes");
        uploader.addEntry("1379691523", teamNumber);
        uploader.addEntry("1855924759", robotName);
        //uploader.upload();
        manager.write(uploader.getUrlData());
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
