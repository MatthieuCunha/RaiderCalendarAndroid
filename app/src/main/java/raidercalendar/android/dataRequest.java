package raidercalendar.android;

import android.util.ArrayMap;
import android.util.EventLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* Handle raidercalendar.android.dataRequest from the different activity
* Later by using the API, for now handle change/request to datadase that should be handled by distant server
 */
public class dataRequest {

    // get groupe joined or created by the user
    public static List<GroupeMembers> getGroupList(String userToken){
        List<User> userList= User.find(User.class,"token = ?", userToken);
        User user = userList.get(0);

        List<GroupeMembers> memberOf = GroupeMembers.find(GroupeMembers.class, "playerid = ?", Long.toString(user.getId()));
        return memberOf;
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


    // set the user Absent for the event
    public static void setAbsent(Long eventId, String token){
        List<User> userList = User.find(User.class, "token = ?", token);
        Long userId = userList.get(0).getId();

        List<EventStatus> eventList = EventStatus.find(EventStatus.class, "eventid = ? and playerid = ?",Long.toString(eventId),Long.toString(userId));
        eventList.get(0).setStatus("ABSENT");
        eventList.get(0).save();

    }

    // set the user available for the event
    public static void setAvailable(Long eventId, String token){
        List<User> userList = User.find(User.class, "token = ?", token);
        Long userId = userList.get(0).getId();

        List<EventStatus> eventList = EventStatus.find(EventStatus.class, "eventid = ? and playerid = ?",Long.toString(eventId),Long.toString(userId));
        eventList.get(0).setStatus("AVAILABLE");
        eventList.get(0).save();
    }

    // set the user Absent for the event
    public static void setAccepted(Long eventId, String token){
        List<User> userList = User.find(User.class, "token = ?", token);
        Long userId = userList.get(0).getId();

        List<EventStatus> eventList = EventStatus.find(EventStatus.class, "eventid = ? and playerid = ?",Long.toString(eventId),Long.toString(userId));
        eventList.get(0).setStatus("ACCEPTED");
        eventList.get(0).save();
    }

    // create a pending request to join a group
    // TODO if enough time
    // for now instantly accepted to group
    public static boolean groupRequest(String groupeToken, String playerToken){
        List<User> userList= User.find(User.class,"token = ?", playerToken);
        List<Groupe> groupeList = Groupe.find(Groupe.class, "joinToken = ? ", groupeToken);

        if(groupeList.size()==0){
            return false;
        }else {

            Groupe groupe = groupeList.get(0);
            User user = userList.get(0);

            GroupeMembers groupeMembers = new GroupeMembers(groupe.getName(), groupe.getId(), user.getId());
            return true;
        }

    }

    // create a new group
    public static void createGroup(String groupeName, String creatorToken){

        List<User> user= User.find(User.class,"token = ?", creatorToken);
        Long creatorId = user.get(0).getId();
        Groupe groupe = new Groupe(groupeName,creatorId);
        groupe.save();

    }

    // create a new event for the group
    public static void createEvent(String name,Date date ,String creatorToken, Long groupeId){

        List<User> user= User.find(User.class,"token = ?", creatorToken);
        Long creatorId = user.get(0).getId();

        // create the event
        eventPreview event = new eventPreview(name,date,creatorId);
        event.save();

        // invite player of the group
        List<GroupeMembers> members = GroupeMembers.find(GroupeMembers.class,"groupeid = ?",Long.toString(groupeId));
        int i = 0;
        while (i < members.size()) {
            Long playerId = members.get(i).getPlayerid();
            EventStatus eventStatus = new EventStatus(event.getId(),playerId,"");
            i++;
        }

    }



}
