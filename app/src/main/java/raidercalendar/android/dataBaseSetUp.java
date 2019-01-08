package raidercalendar.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dataBaseSetUp  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calendar.db"; // nom du fichier pour la base
    private static final String TABLE_Event = "event"; // nom de la table
    private static final String ATTRIBUT_eventID = "eventID"; // liste des attributs
    private static final String ATTRIBUT_eventName = "eventName";
    private static final String ATTRIBUT_eventDate = "eventDate";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public  dataBaseSetUp(Context c){
        super(c, "DB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_event_create =
                "CREATE TABLE " + TABLE_Event + "(" +
                        ATTRIBUT_eventID + " TEXT  primary key," +
                        ATTRIBUT_eventName + " TEXT, " +
                        ATTRIBUT_eventDate + " DATETIME " + ")";
        db.execSQL(table_event_create);
        Log.d("DB",db.getPath());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String table_event_delete = "DROP TABLE IF EXISTS " + TABLE_Event;
        db.execSQL(table_event_delete);

        onCreate(db);
    }


    public void addData(){
        Date date = new Date();

        ContentValues event = new ContentValues();
        event.put(ATTRIBUT_eventID,"ID1");
        event.put(ATTRIBUT_eventName,"Name1");
        event.put(ATTRIBUT_eventDate, dateFormat.format(date));


        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_Event,null,event);
    }

}
