package raidercalendar.android;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class createEventActivity extends AppCompatActivity {

    Context context=createEventActivity.this;
    Button datePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText eventNameEdit = (EditText) findViewById(R.id.eventName);
        //final EditText eventDateEdit = (EditText) findViewById(R.id.eventDate);
       // final EditText eventTimeEdit = (EditText) findViewById(R.id.eventTime);

        // groupChoice
        final Spinner groupSpinner = (Spinner) findViewById(R.id.groupSpinner);
        final List<Groupe> groupList= dataRequest.getGroupList(TokenHolder.getInstance().getToken());
        List<String> groupenameList = new ArrayList<String>();

        int i = 0;
        while (i < groupList.size()) {
            String name = groupList.get(i).getName();
            groupenameList.add(name);
            i++;
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, groupenameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(dataAdapter);


        // Button clic
        Button createEventButton = (Button) findViewById(R.id.createButton);
        final EditText groupeToken = findViewById(R.id.groupeToken);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String eventName = eventNameEdit.getText().toString();

                if (eventName.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Must name the event", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(date==null){
                    Toast toast = Toast.makeText(getApplicationContext(), "Must pick a date", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    int spinnerId = groupSpinner.getSelectedItemPosition();
                    Groupe group = groupList.get(spinnerId);

                    // create event and invitation
                    Date eventDate = date.getTime();
                    Long eventId=dataRequest.createEvent(eventName,eventDate,TokenHolder.getInstance().getToken(),group.getGroupID());

                    Intent intentHome = new Intent(createEventActivity.this, MainCalendarActivity.class);
                    startActivity(intentHome);
                }

            }
        });

        datePick = (Button) findViewById(R.id.buttonDate);
        datePick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                showDateTimePicker();
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

    Calendar date;
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                datePick.setText(date.getTime().toString());
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        datePick.setText(date.getTime().toString());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

    }


}
