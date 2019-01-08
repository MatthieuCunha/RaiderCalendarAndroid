package raidercalendar.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dataBaseSetUp {



    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public  dataBaseSetUp(){
        Date date = new Date();
        eventPreview ev1 = new eventPreview("event1",date);
        eventPreview ev2 = new eventPreview("event2",date);

        ev1.save();
        ev2.save();

    }


    /*public void addData(){
        Date date = new Date();

        ContentValues event = new ContentValues();
        event.put(ATTRIBUT_eventID,"ID1");
        event.put(ATTRIBUT_eventName,"Name1");
        event.put(ATTRIBUT_eventDate, dateFormat.format(date));


        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_Event,null,event);
    }*/

}
