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
import android.widget.ListView;

import java.util.List;

public class manageGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);

        ListView groupListView = (ListView) findViewById(R.id.groupeList);
        final EditText groupNameEdit = (EditText) findViewById(R.id.groupName);
        Button buttonCreateGroup = (Button) findViewById(R.id.buttonCreateGroup) ;
        buttonCreateGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // call to the fake API
                String groupName= groupNameEdit.getText().toString();
                dataRequest.createGroup(groupName,TokenHolder.getInstance().getToken());
                //reload
                finish();
                startActivity(getIntent());
            }
        });


        // load group into listView
        List<Groupe> groupList= dataRequest.getGroupList(TokenHolder.getInstance().getToken());


        ArrayAdapter<Groupe> groupAdaptater = new groupAdaptater(manageGroupActivity.this, R.layout.listgroup, groupList);
        groupListView.setAdapter(groupAdaptater);
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
                Intent intentHome = new Intent(manageGroupActivity.this, MainCalendarActivity.class);
                startActivity(intentHome);
                break;
            case R.id.action_joingroupe:
                Intent intentJoin = new Intent(manageGroupActivity.this, joinGroupRequestActivity.class);
                startActivity(intentJoin);
                break;
            case R.id.action_managegroupe:
                Intent intentManage = new Intent(manageGroupActivity.this, manageGroupActivity.class);
                startActivity(intentManage);
                break;
            case R.id.action_createEvent:
                Intent intentCreate = new Intent(manageGroupActivity.this, createEventActivity.class);
                startActivity(intentCreate);
                break;
        }

        return true;
    }



}
