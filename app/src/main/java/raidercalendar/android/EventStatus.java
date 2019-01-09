package raidercalendar.android;

import com.orm.SugarRecord;

public class EventStatus extends SugarRecord<EventStatus> {

   // private Long uniqID;
    private Long eventId;
    private Long playerid;
    private String status;

    public EventStatus(){}

    public EventStatus(long eventId,long playerId){
       // this.uniqID=this.getId(); // recuperation de l'id sugarORM
        this.eventId=eventId;
        this.playerid=playerId;
        this.status="PENDING";
    }

    public Long getID() {
        return this.getId();
    }

  //  public void setUniqID(Long uniqID) {
  //      this.uniqID = uniqID;
  //  }

    public Long getEventID() {
        return eventId;
    }

    public void setEventID(Long eventId) {
        this.eventId = eventId;
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


}
