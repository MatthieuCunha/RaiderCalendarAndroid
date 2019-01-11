package raidercalendar.android;

import com.orm.SugarRecord;

// relation object between groupe instead of 2 side relation to prevent ORM loop loading problem
public class GroupeMembers extends SugarRecord<GroupeMembers> {

    private String name;
    private Long groupeid;
    private Long playerid;

    // required by sugarORM
    public GroupeMembers(){}

    public GroupeMembers(String name, Long groupeid, Long playerid){
        this.name=name;
        this.groupeid=groupeid;
        this.playerid=playerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGroupeid() {
        return groupeid;
    }

    public void setGroupeid(long groupeid) {
        this.groupeid = groupeid;
    }

    public long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }



}
