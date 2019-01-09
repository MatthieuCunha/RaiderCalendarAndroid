package raidercalendar.android;

import android.util.ArrayMap;
import android.util.EventLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* Handle raidercalendar.android.dataRequest from the different activity
* Later by using the API, for now Hard Coded data
 */
public class dataRequest {

    public static String[] getGroupList(String userID){
      return null;
    }

    public static String[] getAvailableGame(){
        return null;
    }

    /*
    * return the list of Event For this raidercalendar.android.User
    */
    public static List<eventPreview> getEventList(String userToken){

        List<eventPreview> eventPreviewList=new ArrayList<>();

        List<User> user = User.find(User.class,"token = ?", userToken);
        String userID=Long.toString(user.get(0).getID());

       // List<EventStatus> eventStatusListFull=  EventStatus.listAll(EventStatus.class);

        List<EventStatus> eventStatusList=EventStatus.find(EventStatus.class,"playerid = ?",userID);
        int i = 0;
        while (i < eventStatusList.size()) {
            Long eventID = eventStatusList.get(i).getEventID();
            String status =  eventStatusList.get(i).getStatus();

            eventPreview event = eventPreview.findById(eventPreview.class,eventID);
            event.setStatus(status);
            eventPreviewList.add(event);

            i++;
        }

        return eventPreviewList;
    }

    public static List<eventPreview> getEventListByDay(String userToken, Date day){
        List<eventPreview> eventPreviewList=new ArrayList<>();

        List<eventPreview> eventPreviewListFull=dataRequest.getEventList(userToken);

        int i = 0;
        while (i < eventPreviewListFull.size()) {
            Date date = eventPreviewListFull.get(i).getDate();
            if(date.getDay() == day.getDay() && date.getMonth() == day.getMonth() && date.getYear() == day.getYear()){
                eventPreviewList.add(eventPreviewListFull.get(i));
            }
            i++;
        }
        return eventPreviewList;
    }

}
