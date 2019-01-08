package raidercalendar.android;

import com.orm.SugarRecord;

public class User extends SugarRecord<User> {

  // private Long ID;
   private String name;
   private String password;
   private String token; // fix ici, variable si update pour utiliser l'API


    public User(){
       // this.ID=this.getId(); // recuperation de l'id sugarORM

    }

    public User(String name,String password, String token){
        // uniquement les temps des test
      //  this.ID=this.getId(); // recuperation de l'id sugarORM
        this.name=name;
        this.password=password;
        this.token=token;
    }

    public Long getID() {
        return this.getId();
    }

 //  public void setID(Long ID) {
  //      this.ID = ID;
  //  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
