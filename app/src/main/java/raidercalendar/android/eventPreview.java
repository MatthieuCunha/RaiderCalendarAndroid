package raidercalendar.android;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

/*
* Class containing the minimal data for Event preview on the calendar
*
 */
public class eventPreview  extends SugarRecord<eventPreview> {

   // private Long ID;
    private Date date;
    private String name;
    private Long creatorid;


    @Ignore
    private String status;

    public eventPreview(){
       // this.ID=this.getId(); // recuperation de l'id sugarORM
    };

    public eventPreview(String name,Date date,Long creatorid){
        this.name=name;
        this.date=date;
       // this.ID=this.getId(); // recuperation de l'id sugarORM
        this.creatorid=creatorid;


    };

    public Long getID() {
        return this.getId();
    }

   // public void setID(Long ID) {
    //    this.ID = ID;
    //}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }
}
