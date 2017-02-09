package group9.hsr.ch.businesstravelagent.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import group9.hsr.ch.businesstravelagent.Model.ConnectionDate;
import group9.hsr.ch.businesstravelagent.Model.ConnectionTime;
import group9.hsr.ch.businesstravelagent.Model.OppositeDirection;
import group9.hsr.ch.businesstravelagent.R;

public class MainActivity extends AppCompatActivity {
    /*private IOpenTransportRepository repo;

    public MainActivity() throws OpenDataTransportException {
        repo = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
    }

    ConnectionList connectionList = repo.searchConnections("Buchs SG", "Zürich HB");
    */

    ConnectionDate connectionDate;
    ConnectionTime connectionTime;
    OppositeDirection oppositeDirection;

    public MainActivity() {
        connectionDate = new ConnectionDate(MainActivity.this);
        connectionTime = new ConnectionTime(MainActivity.this);
        oppositeDirection = new OppositeDirection(MainActivity.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionDate.Register();
        connectionDate.ShowCurrentDateOnButton();
        connectionTime.Register();
        connectionTime.ShowCurrentTimeOnButton();
        oppositeDirection.Register();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_menu);
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
