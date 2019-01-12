package raidercalendar.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginPage extends AppCompatActivity {

    Button validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final Context c=this;
        dataBaseSetUp db = new dataBaseSetUp();
       // db.addData();

       final EditText login = findViewById(R.id.login);
        final EditText password = findViewById(R.id.password);

        validerButton = (Button) findViewById(R.id.validerLogin) ;
        validerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(loginPage.this, MainCalendarActivity.class);

                /* requete API et recuperation du token*/
                String token=dataRequest.login(login.getText().toString(),password.getText().toString());
                if(token==null){
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong login info", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    TokenHolder.getInstance().setToken(token);
                    startActivity(intent);
                }

            }
        });

    }
}
