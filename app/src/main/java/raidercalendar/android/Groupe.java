package raidercalendar.android;

import com.orm.SugarRecord;

public class Groupe extends SugarRecord<Groupe> {

    private Long groupID;
    private String name;
    private Long[] membersID;

    public Groupe(){
        this.groupID=super.id;
    };

    public Groupe(String name){
        this.name=name;
        this.groupID=super.id;
    };

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getMembersID() {
        return membersID;
    }

    public void setMembersID(Long[] membersID) {
        this.membersID = membersID;
    }

}
