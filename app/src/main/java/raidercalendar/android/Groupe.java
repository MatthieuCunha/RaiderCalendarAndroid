package raidercalendar.android;

import com.orm.SugarRecord;

import java.security.SecureRandom;
import java.util.List;

public class Groupe extends SugarRecord<Groupe> {

   // private Long groupID;
    private String name;
    private String jointoken;
    private Long creatorId;

    public Groupe(){
       // this.groupID=this.getId(); // recuperation de l'id sugarORM

    };

    public Groupe(String name, Long creatorId){
        this.name=name;
        this.jointoken=randomToken.shortToken(8);
        this.creatorId=creatorId;
      //  this.groupID=this.getId(); // recuperation de l'id sugarORM
    };

    public Long getGroupID() {
            return this.getId();
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

    public String getJoinToken() {
        return jointoken;
    }

    public void setJoinToken(String joinToken) {
        this.jointoken = joinToken;
    }

}
