package raidercalendar.android;

import com.orm.SugarRecord;

public class EventStatus extends SugarRecord<EventStatus> {

   // private Long uniqID;
    private Long event_id;
    private Long player_id;
    private String status;

    public EventStatus(){
        //this.uniqID=this.getId(); // recuperation de l'id sugarORM
    }

    public EventStatus(long event_id,long player_id){
       // this.uniqID=this.getId(); // recuperation de l'id sugarORM
        this.event_id=event_id;
        this.player_id=player_id;
        this.status="PENDING";
    }

    public Long getID() {
        return this.getId();
    }

  //  public void setUniqID(Long uniqID) {
  //      this.uniqID = uniqID;
  //  }

    public Long getEventID() {
        return event_id;
    }

    public void setEventID(Long event_id) {
        this.event_id = event_id;
    }

    public Long getPlayerID() {
        return player_id;
    }

    public void setPlayerID(Long player_id) {
        this.player_id = player_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void save(){

    }

    public void load(String id){

    }

}
