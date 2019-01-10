package raidercalendar.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class eventDetailActivity extends AppCompatActivity {

    private Long idEvent;
    private TextView eventDate;
    private TextView eventName;
    private ListView listPending;
    private ListView listAvailable;
    private ListView listAccepted;
    private ListView listAbsent;

    ArrayList<EventStatus> listPendingItems=new ArrayList<EventStatus>();
    ArrayList<EventStatus> listAvailableItems=new ArrayList<EventStatus>();
    ArrayList<EventStatus> listAcceptedItems=new ArrayList<EventStatus>();
    ArrayList<EventStatus> listAbsentItems=new ArrayList<EventStatus>();

    playerAdaptater absentAdaptater;
    playerAdaptater pendingAdaptater;
    playerAdaptater acceptedAdaptater;
    playerAdaptater availableAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        this.idEvent=getIntent().getExtras().getLong("idEvent");

        eventPreview event = eventPreview.findById(eventPreview.class,this.idEvent);

        // get item from view
         eventName = (TextView) findViewById(R.id.title);
        eventName.setText(event.getName());
         eventDate = (TextView) findViewById(R.id.date);
        eventDate.setText(event.getDate().toString());
        listAbsent = (ListView) findViewById(R.id.listAbsent);
        listAccepted = (ListView) findViewById(R.id.listAccepted);
        listAvailable = (ListView) findViewById(R.id.listAvailable);
        listPending = (ListView) findViewById(R.id.listPending);

        // create custom adaptater
        absentAdaptater = new playerAdaptater(eventDetailActivity.this, R.layout.listplayers,listAbsentItems);
        pendingAdaptater = new playerAdaptater(eventDetailActivity.this, R.layout.listplayers,listPendingItems);
        availableAdaptater = new playerAdaptater(eventDetailActivity.this, R.layout.listplayers,listAvailableItems);
        acceptedAdaptater = new playerAdaptater(eventDetailActivity.this, R.layout.listplayers,listAcceptedItems);

        // link adaptater to listView

        listAbsent.setAdapter(absentAdaptater);
        listPending.setAdapter(pendingAdaptater);
        listAvailable.setAdapter(availableAdaptater);
        listAccepted.setAdapter(acceptedAdaptater);

        // load player from DB
        List<EventStatus> listPlayerFull = EventStatus.listAll(EventStatus.class);
        List<EventStatus> listPlayer = EventStatus.find(EventStatus.class, "eventid = ?", Long.toString(this.idEvent));
        int i = 0;
        while (i < listPlayer.size()) {
            // check le status, et ranger dans la liste view correspondante
                String status=listPlayer.get(i).getStatus();

                switch (status){
                    case "PENDING":
                        listPendingItems.add(listPlayer.get(i));
                        pendingAdaptater.notifyDataSetChanged();
                        break;
                    case "ABSENT":
                        listAbsentItems.add(listPlayer.get(i));
                        absentAdaptater.notifyDataSetChanged();
                        break;
                    case "ACCEPTED":
                        listAcceptedItems.add(listPlayer.get(i));
                        acceptedAdaptater.notifyDataSetChanged();
                        break;
                    case "AVAILABLE":
                        listAvailableItems.add(listPlayer.get(i));
                        availableAdaptater.notifyDataSetChanged();
                        break;

                }

            i++;
        }

    }
}
