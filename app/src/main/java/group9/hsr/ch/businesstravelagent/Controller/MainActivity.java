package group9.hsr.ch.businesstravelagent.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import group9.hsr.ch.businesstravelagent.Model.TransportConnection;
import group9.hsr.ch.businesstravelagent.Model.TransportDate;
import group9.hsr.ch.businesstravelagent.Model.TransportSearch;
import group9.hsr.ch.businesstravelagent.Model.TransportTime;
import group9.hsr.ch.businesstravelagent.Model.OppositeDirection;
import group9.hsr.ch.businesstravelagent.R;

public class MainActivity extends AppCompatActivity {

    private TransportDate transportDate;
    private TransportTime transportTime;
    private OppositeDirection oppositeDirection;
    private TransportSearch transportSearch;

    public MainActivity() {
        transportDate = new TransportDate(MainActivity.this);
        transportTime = new TransportTime(MainActivity.this);
        oppositeDirection = new OppositeDirection(MainActivity.this);
        transportSearch = new TransportSearch(MainActivity.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transportDate.Register();
        transportDate.ShowCurrentDateOnButton();
        transportTime.Register();
        transportTime.ShowCurrentTimeOnButton();
        oppositeDirection.Register();
        //transportSearch.Register();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_menu);
        myToolbar.setTitleTextAppearance(this, R.style.ActionToolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_About: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
