package raidercalendar.android;

import java.util.Date;

/*
* Class containing the minimal data for Event preview on the calendar
*
 */
public class eventPreview {
    private String ID;
    private Date date;
    private String Name;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public void save(){

    }

    public void load(String id){

    }

}
