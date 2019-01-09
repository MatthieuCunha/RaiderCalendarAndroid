package raidercalendar.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class eventDetailActivity extends AppCompatActivity {

    private Long idEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        this.idEvent=getIntent().getExtras().getLong("idEvent");

        eventPreview event = eventPreview.findById(eventPreview.class,this.idEvent);

        TextView eventName = (TextView) findViewById(R.id.title);
        eventName.setText(event.getName());

        TextView eventDate = (TextView) findViewById(R.id.date);
        eventDate.setText(event.getDate().toString());

        List<EventStatus> listPlayer = EventStatus.find(EventStatus.class, "eventid =", Long.toString(this.idEvent));

        int i = 0;
        while (i < listPlayer.size()) {
            // check le status, et ranger dans la liste view correspondante

            i++;
        }

    }
}
