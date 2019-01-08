package raidercalendar.android;

import com.orm.SugarRecord;

import java.util.Date;

/*
* Class containing the minimal data for Event preview on the calendar
*
 */
public class eventPreview  extends SugarRecord<eventPreview> {

    private Long ID;
    private Date date;
    private String name;


    public eventPreview(){};

    public eventPreview(String name,Date date){
        this.name=name;
        this.date=date;
        this.ID=super.id;
    };

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

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


}
