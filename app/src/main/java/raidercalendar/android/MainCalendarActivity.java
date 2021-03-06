package raidercalendar.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainCalendarActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    TextView currentMonth;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("MainCalendar");
        actionbar.show();

        currentMonth = (TextView) findViewById(R.id.month);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        this.loadEvent();

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                //get day, check array , list event en popup

                List<String> eventNameList = new ArrayList<String>();

                final List<eventPreview> eventPreviewList=dataRequest.getEventListByDay(TokenHolder.getInstance().getToken(),dateClicked);

                int i = 0;
                while (i < eventPreviewList.size()) {

                    String name = eventPreviewList.get(i).getName();
                    eventNameList.add(name);
                    i++;
                }

                final CharSequence[] events = eventNameList.toArray(new CharSequence[eventNameList.size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainCalendarActivity.this);
                builder.setTitle("Event Choice");
                builder.setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on event[which]
                       Long id = eventPreviewList.get(which).getId();
                       Intent intent = new Intent(MainCalendarActivity.this, eventDetailActivity.class);
                       intent.putExtra("idEvent",id);
                       startActivity(intent);

                    }
                });
                if(eventNameList.size()>0) {
                    builder.show();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                currentMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }


    private void loadEvent(){

       // List<eventPreview> eventPreviewListFull=eventPreview.listAll(eventPreview.class);
        List<eventPreview> eventPreviewList=dataRequest.getEventList(TokenHolder.getInstance().getToken());
        int i = 0;
        while (i < eventPreviewList.size()) {

            String name = eventPreviewList.get(i).getName();
            String status = eventPreviewList.get(i).getStatus();
            Date date = eventPreviewList.get(i).getDate();
            Long eventId = eventPreviewList.get(i).getId();

           String Status= dataRequest.getStatus(eventId,TokenHolder.getInstance().getToken());
           int color = Color.GRAY;
            switch (Status){
                case "AVAILABLE":
                    color = Color.BLUE;
                    break;
                case "ACCEPTED":
                    color = Color.GREEN;
                    break;
                case "ABSENT":
                    color = Color.RED;
                    break;
                case "PENDING":
                    color = Color.GRAY;
                    break;

            }

            Event ev1 = new Event(color, date.getTime() , name);
            compactCalendar.addEvent(ev1);

            i++;
        }

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
                Intent intentHome = new Intent(MainCalendarActivity.this, MainCalendarActivity.class);
                startActivity(intentHome);
                break;
            case R.id.action_joingroupe:
                Intent intentJoin = new Intent(MainCalendarActivity.this, joinGroupRequestActivity.class);
                startActivity(intentJoin);
                break;
            case R.id.action_managegroupe:
                Intent intentManage = new Intent(MainCalendarActivity.this, manageGroupActivity.class);
                startActivity(intentManage);
                break;
            case R.id.action_createEvent:
                Intent intentCreate = new Intent(MainCalendarActivity.this, createEventActivity.class);
                startActivity(intentCreate);
                break;
        }

        return true;
    }

}

