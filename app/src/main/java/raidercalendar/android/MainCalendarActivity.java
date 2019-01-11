package raidercalendar.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

            Event ev1 = new Event(Color.GREEN, date.getTime() , name);
            compactCalendar.addEvent(ev1);

            i++;
        }

    }

}
