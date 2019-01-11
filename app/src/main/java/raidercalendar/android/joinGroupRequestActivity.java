package raidercalendar.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class joinGroupRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group_request);

        Button sendRequestButton = (Button) findViewById(R.id.buttonSend);
        final EditText groupeToken = findViewById(R.id.groupeToken);

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // call to the fake API
                boolean success=dataRequest.groupRequest(groupeToken.getText().toString(),TokenHolder.getInstance().getToken());

                if (success){
                    Toast toast = Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Unknown token", Toast.LENGTH_SHORT);
                    toast.show();
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
                Intent intentHome = new Intent(joinGroupRequestActivity.this, MainCalendarActivity.class);
                startActivity(intentHome);
                break;
            case R.id.action_joingroupe:
                Intent intentJoin = new Intent(joinGroupRequestActivity.this, joinGroupRequestActivity.class);
                startActivity(intentJoin);
                break;
            case R.id.action_managegroupe:
                Intent intentManage = new Intent(joinGroupRequestActivity.this, manageGroupActivity.class);
                startActivity(intentManage);
                break;
            case R.id.action_createEvent:
                Intent intentCreate = new Intent(joinGroupRequestActivity.this, createEventActivity.class);
                startActivity(intentCreate);
                break;
        }

        return true;
    }


}
