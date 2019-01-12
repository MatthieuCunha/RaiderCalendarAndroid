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


        User user1=new User("user1","password","tmpToken");
        User user2=new User("user2","password","token1");
        User user3=new User("user3","password","token2");
        User user4=new User("user4","password","token3");
        User user5=new User("user5","password","token4");
        User user6=new User("user6","password","token5");

        user1.save();
        user2.save();
        user3.save();
        user4.save();
        user5.save();
        user6.save();

        Date date = new Date();
        eventPreview ev1 = new eventPreview("event1",date,user1.getID());
        eventPreview ev2 = null;
        eventPreview ev3 = null;

        try {
            ev1.save();
            ev2 = new eventPreview("event2",dateFormat.parse("2019-02-12 21:00:00"),user1.getID());
            ev2.save();
            ev3 = new eventPreview("If26",dateFormat.parse("2019-01-22 14:00:00"),user1.getID());
            ev3.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        EventStatus eventStatus1 = new EventStatus(ev1.getID(),user1.getID(),"Tank");
        EventStatus eventStatus2 = new EventStatus(ev2.getID(),user1.getID(),"Range");
        EventStatus eventStatus3 = new EventStatus(ev1.getID(),user2.getID(),"Melee");

        EventStatus eventStatus4 = new EventStatus(ev1.getID(),user3.getID(),"Range");
        EventStatus eventStatus5 = new EventStatus(ev1.getID(),user4.getID(),"Heal");
        EventStatus eventStatus6 = new EventStatus(ev1.getID(),user5.getID(),"Melee");
        EventStatus eventStatus7 = new EventStatus(ev1.getID(),user6.getID(),"Range");

        EventStatus eventStatus8 = new EventStatus(ev3.getID(),user1.getID(),"dfsvrsvs");

        eventStatus1.save();
        eventStatus2.save();
        eventStatus3.save();
        eventStatus4.save();
        eventStatus5.save();
        eventStatus6.save();
        eventStatus7.save();
        eventStatus8.save();

        Groupe groupe1 = new Groupe("groupe1",user1.getId());
        Groupe groupe2 = new Groupe("groupe2",user2.getId());
        groupe2.setJoinToken("tokentest");
        Groupe groupe3 = new Groupe("groupe3",user1.getId());

        groupe1.save();
        groupe2.save();
        groupe3.save();

        GroupeMembers member1 = new GroupeMembers("group1",groupe1.getId(),user1.getId());
        GroupeMembers member2 = new GroupeMembers("group1",groupe1.getId(),user2.getId());
        GroupeMembers member3 = new GroupeMembers("group1",groupe1.getId(),user3.getId());
        GroupeMembers member4 = new GroupeMembers("group2",groupe2.getId(),user3.getId());

        member1.save();
        member2.save();
        member3.save();
        member4.save();

    }

}
