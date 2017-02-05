package group9.hsr.ch.businesstravelagent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by station on 05/02/2017.
 */

public class AboutActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar tb = (Toolbar) findViewById(R.id.app_menu);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setTitle("@string/about_name");
    }
}
