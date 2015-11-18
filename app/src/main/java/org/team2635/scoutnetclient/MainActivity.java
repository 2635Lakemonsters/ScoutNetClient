package org.team2635.scoutnetclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import org.team2635.scoutnetclient.fragments.AssignmentsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu, menu);
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

    public void showAssignment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AssignmentsFragment fragment = new AssignmentsFragment();
        fragmentTransaction.add(R.id.assignment_container, fragment);
        fragmentTransaction.commit();

    }

}
