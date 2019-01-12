package raidercalendar.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class createEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        EditText eventNameEdit = (EditText) findViewById(R.id.eventName);
        final String eventName = eventNameEdit.getText().toString();

        EditText eventDateEdit = (EditText) findViewById(R.id.eventDate);
        EditText eventTimeEdit = (EditText) findViewById(R.id.eventTime);

        // groupChoice
        Spinner groupSpinner = (Spinner) findViewById(R.id.groupSpinner);
        List<Groupe> groupList= dataRequest.getGroupList(TokenHolder.getInstance().getToken());
        ArrayAdapter<Groupe> groupAdaptater = new groupAdaptater(createEventActivity.this, R.layout.listgroup, groupList);
        groupSpinner.setAdapter(groupAdaptater);

        // Button clic
        Button createEventButton = (Button) findViewById(R.id.createButton);
        final EditText groupeToken = findViewById(R.id.groupeToken);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // call to the fake API
                String toastText;

                if(eventName.length()==0){
                    toastText="Must name the event";
                    Toast toast = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    // créer l'event
                }


            }
        });


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
