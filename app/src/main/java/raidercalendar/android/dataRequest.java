package raidercalendar.android;

import android.util.ArrayMap;

import java.util.ArrayList;
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

        List<EventStatus> eventStatusList=EventStatus.find(EventStatus.class,"playerID = ?",userID);
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

}
