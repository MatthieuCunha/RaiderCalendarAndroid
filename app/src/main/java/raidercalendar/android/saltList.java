package raidercalendar.android;

import com.orm.SugarRecord;

public class saltList extends SugarRecord<saltList> {

    private String salt;
    private String password;
    private Long userid;

    public saltList(Long userid,String password,String salt){
        this.salt=salt;
        this.userid=userid;
        this.password=password;
    }

    public saltList(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String  getSalt() {
        return salt;
    }

    public void setSalt(String  salt) {
        this.salt = salt;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
