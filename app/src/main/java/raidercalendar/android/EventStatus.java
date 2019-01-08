package raidercalendar.android;

import com.orm.SugarRecord;

public class EventStatus extends SugarRecord<EventStatus> {

    private Long uniqID;
    private Long eventID;
    private Long playerID;
    private String status;

    public EventStatus(){
        this.uniqID=super.id;
    }

    public EventStatus(long eventId,long playerID){
        this.uniqID=super.id;
        this.eventID=eventId;
        this.playerID=playerID;
        this.status="PENDING";
    }

    public Long getUniqID() {
        return uniqID;
    }

    public void setUniqID(Long uniqID) {
        this.uniqID = uniqID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
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
