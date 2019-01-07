package raidercalendar.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginPage extends AppCompatActivity {

    Button validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        validerButton = (Button) findViewById(R.id.validerLogin) ;
        validerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(loginPage.this, MainCalendarActivity.class);

                /* requete API et recuperation du token*/
                TokenHolder.getInstance().setToken("tmpToken");

                startActivity(intent);
            }
        });

    }
}
