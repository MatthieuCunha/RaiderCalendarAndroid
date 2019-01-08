package raidercalendar.android;

import com.orm.SugarRecord;

import java.util.List;

public class Groupe extends SugarRecord<Groupe> {

   // private Long groupID;
    private String name;
    private List<Long> members_id;

    public Groupe(){
       // this.groupID=this.getId(); // recuperation de l'id sugarORM

    };

    public Groupe(String name){
        this.name=name;
      //  this.groupID=this.getId(); // recuperation de l'id sugarORM
    };

    public Long getGroupID() {
            return this.getId();
    }

    public void addMember(Long playerID){
        members_id.add(playerID);
    }

   // public void setGroupID(Long groupID) {
    //    this.groupID = groupID;
   // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getMembersID() {
        return members_id;
    }

    public void setMembersID(Long[] membersID) {
        this.members_id = members_id;
    }

}
