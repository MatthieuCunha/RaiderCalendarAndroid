package raidercalendar.android;

import android.util.ArrayMap;
import android.util.EventLog;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.acl.Group;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
* Handle raidercalendar.android.dataRequest from the different activity
* Later by using the API, for now handle change/request to datadase that should be handled by distant server
 */
public class dataRequest {

    // get groupe joined or created by the user
    public static List<Groupe> getGroupList(String userToken){

        List<Groupe> groupList = new ArrayList<Groupe>();

        List<User> userList= User.find(User.class,"token = ?", userToken);
        User user = userList.get(0);

        List<GroupeMembers> memberOf = GroupeMembers.find(GroupeMembers.class, "playerid = ?", Long.toString(user.getId()));
        int i = 0;
        while (i < memberOf.size()) {
            Long groupId = memberOf.get(i).getGroupeid();
            Groupe groupe = Groupe.findById(Groupe.class,groupId);
            groupList.add(groupe);

            i++;
        }


        return groupList;
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
    public static String groupRequest(String groupeToken, String playerToken){
        List<User> userList= User.find(User.class,"token = ?", playerToken);
        List<Groupe> groupeList = Groupe.find(Groupe.class, "jointoken = ? ", groupeToken);

        if(groupeList.size()==0){
            return "Unknown token";
        }else {

            Groupe groupe = groupeList.get(0);
            User user = userList.get(0);

            List<GroupeMembers> groupeMemberList = GroupeMembers.find(GroupeMembers.class,"groupid = ? and playerid= ?", Long.toString(groupe.getId()),Long.toString(user.getId()));
            if(groupeMemberList.size()==0) {
                GroupeMembers groupeMembers = new GroupeMembers(groupe.getName(), groupe.getId(), user.getId());
                return "Request Sent";
            }else{
                return "You are already a member of this group";
            }

        }

    }

    // create a new group
    public static void createGroup(String groupeName, String creatorToken){

        List<User> user= User.find(User.class,"token = ?", creatorToken);
        Long creatorId = user.get(0).getId();
        Groupe group = new Groupe(groupeName,creatorId);
        group.save();

        // ajoute le createur au groupe
        GroupeMembers gm = new GroupeMembers(group.getName(),group.getId(),creatorId);
        gm.save();


    }

    // create a new event for the group
    public static Long createEvent(String name,Date date ,String creatorToken, Long groupeId){

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
            eventStatus.save();
            i++;
        }

        return event.getId();
    }

   /* public static void createInvitation(Long eventId, Long groupID){
        List<GroupeMembers> memberOf = GroupeMembers.find(GroupeMembers.class, "groupeid = ?", Long.toString(groupID));
        int i = 0;
        while (i < memberOf.size()) {
            Long playerId = memberOf.get(i).getPlayerid();
            EventStatus eventStatus=new EventStatus(eventId, playerId, "");
            eventStatus.save();

            i++;
        }

    }*/

   public static String login(String login,String password){

       List<User> userList= User.find(User.class,"name = ?",login);

       // no user with this name
       if(userList.size()==0){
           return null;
       }else{
           User user=userList.get(0);
           // hash password et comparer avec le hash en DB
            List<saltList> loginInfoList = saltList.find(saltList.class,"userid = ?", Long.toString(user.getId()));
            saltList loginInfo = loginInfoList.get(0);

            if(get_SHA_512_SecurePassword(password,loginInfo.getSalt())==loginInfo.getPassword()){
               return user.getToken();
           }
       }
       return null;
   }

   public static String createAccount(String login, String password){
        String message="";

       List<User> userList= User.find(User.class,"name = ?",login);

       // no user with this name
       if(userList.size()==0){
           String salt=makeSalt();
           String passwordHash=get_SHA_512_SecurePassword(password,salt.toString());
           // need longer token for real use, but only 1 rreal user anyway for now.
            User user = new User(login,randomToken.shortToken(18));
            user.save();

            saltList saltList=new saltList(user.getId(),passwordHash,salt);
            saltList.save();
            message="OK";

       }else{
           message="Login name already used";
       }

       return message;
   }


   // don't work
   private static String hashPBKDF(String password,byte[] salt){
       byte[] hash=new byte[0];
       SecureRandom random = new SecureRandom();
       KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
       SecretKeyFactory factory = null;
       try {
           factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       try {
          hash = factory.generateSecret(spec).getEncoded();
       } catch (InvalidKeySpecException e) {
           e.printStackTrace();
       }

       String passwordHash="";
       try {
           passwordHash = new String(hash,"UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
    return passwordHash;
   }

   private static String makeSalt(){
       SecureRandom random = new SecureRandom();
       byte[] salt = new byte[16];
       random.nextBytes(salt);
       return salt.toString();
   }

   // too weak for password, but this project is useless anyway
    public static String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
