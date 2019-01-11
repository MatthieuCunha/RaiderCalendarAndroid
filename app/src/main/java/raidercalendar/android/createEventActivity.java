package raidercalendar.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class createEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            // back button
            case android.R.id.home:
                Intent intentHome = new Intent(createEventActivity.this, MainCalendarActivity.class);
                startActivity(intentHome);
                break;
            case R.id.action_joingroupe:
                Intent intentJoin = new Intent(createEventActivity.this, joinGroupRequestActivity.class);
                startActivity(intentJoin);
                break;
            case R.id.action_managegroupe:
                Intent intentManage = new Intent(createEventActivity.this, manageGroupActivity.class);
                startActivity(intentManage);
                break;
            case R.id.action_createEvent:
                Intent intentCreate = new Intent(createEventActivity.this, createEventActivity.class);
                startActivity(intentCreate);
                break;
        }

        return true;
    }

}
