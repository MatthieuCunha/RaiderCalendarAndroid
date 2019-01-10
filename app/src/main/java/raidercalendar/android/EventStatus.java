package raidercalendar.android;

import com.orm.SugarRecord;

public class EventStatus extends SugarRecord<EventStatus> {

   // private Long uniqID;
    private Long eventid;
    private Long playerid;
    private String status;


    private String role;

    public EventStatus(){}

    public EventStatus(long eventId,long playerId, String role){
       // this.uniqID=this.getId(); // recuperation de l'id sugarORM
        this.eventid=eventId;
        this.playerid=playerId;
        this.status="PENDING";
        this.role=role;
    }

    public Long getID() {
        return this.getId();
    }

  //  public void setUniqID(Long uniqID) {
  //      this.uniqID = uniqID;
  //  }

    public Long getEventID() {
        return eventid;
    }

    public void setEventID(Long eventId) {
        this.eventid = eventId;
    }

    public Long getPlayerID() {
        return playerid;
    }

    public void setPlayerID(Long playerId) {
        this.playerid = playerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
