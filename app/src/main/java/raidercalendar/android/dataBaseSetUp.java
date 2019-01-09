package raidercalendar.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.orm.SugarRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class dataBaseSetUp {



    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /*
    * create some data in the database for test purpose
     */
    public  dataBaseSetUp(){

        // delete previous data
        eventPreview.deleteAll(eventPreview.class);
        User.deleteAll(User.class);
        EventStatus.deleteAll(EventStatus.class);
        Groupe.deleteAll(Groupe.class);

        Date date = new Date();
        eventPreview ev1 = new eventPreview("event1",date);
        eventPreview ev2 = null;
        try {
            ev2 = new eventPreview("event2",dateFormat.parse("2019-02-12 21:00:00"));
            ev2.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ev1.save();

        User user1=new User("user1","password","tmpToken");
        User user2=new User("user2","password","token");

        user1.save();
        user2.save();

        EventStatus eventStatus1 = new EventStatus(ev1.getID(),user1.getID());
        EventStatus eventStatus2 = new EventStatus(ev2.getID(),user1.getID());
        EventStatus eventStatus3 = new EventStatus(ev1.getID(),user2.getID());

        eventStatus1.save();
        eventStatus2.save();
        eventStatus3.save();


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
