package raidercalendar.android;

import android.content.Intent;
import android.media.session.MediaSession;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class eventDetailActivity extends AppCompatActivity {

    private Long idEvent;
    private eventPreview event;
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

        // menu bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Long id = getIntent().getExtras().getLong("idEvent");
        this.idEvent=id;
        event=dataRequest.getEventPreview(this.idEvent);

        Button absentButton = (Button) findViewById(R.id.absentButton) ;
        absentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // call to the fake API
                dataRequest.setAbsent(id, TokenHolder.getInstance().getToken());
                //reload
                finish();
                startActivity(getIntent());
            }
        });

        Button presentButton = (Button) findViewById(R.id.presentButton) ;
        presentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // call to the fake API
                dataRequest.setAvailable(id, TokenHolder.getInstance().getToken());
                //reload
                finish();
                startActivity(getIntent());
            }
        });

        // get item from view
         eventName = (TextView) findViewById(R.id.title);
        eventName.setText(event.getName()+" By "+dataRequest.getNameById(event.getCreatorid()));
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


        // listView on CLick switch target status
        listAccepted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // only event creator can change other player status
                EventStatus eventStatus= (EventStatus) listAccepted.getItemAtPosition(position);
                if(event.getCreatorid()==dataRequest.getMyId(TokenHolder.getInstance().getToken())){

                    dataRequest.setAvailableById(eventStatus.getEventID(),eventStatus.getPlayerID());

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        listAccepted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventStatus eventStatus= (EventStatus) listAccepted.getItemAtPosition(position);
                // only event creator can change other player status
                if(event.getCreatorid()==dataRequest.getMyId(TokenHolder.getInstance().getToken())) {
                    dataRequest.setAcceptedById(eventStatus.getEventID(), eventStatus.getPlayerID());

                    finish();
                    startActivity(getIntent());
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
                Intent intentHome = new Intent(eventDetailActivity.this, MainCalendarActivity.class);
                startActivity(intentHome);
                break;
            case R.id.action_joingroupe:
                Intent intentJoin = new Intent(eventDetailActivity.this, joinGroupRequestActivity.class);
                startActivity(intentJoin);
                break;
            case R.id.action_managegroupe:
                Intent intentManage = new Intent(eventDetailActivity.this, manageGroupActivity.class);
                startActivity(intentManage);
                break;
            case R.id.action_createEvent:
                Intent intentCreate = new Intent(eventDetailActivity.this, createEventActivity.class);
                startActivity(intentCreate);
                break;
        }

        return true;
    }


}
