package es.ujaen.git.sm1718_G04_practica2;

/**
 * Created by usuario on 04/10/2017.
 */

public class PersonalData {
    protected String name="";
    protected String surname="";

    protected String user="";
    protected String pass="";

    public PersonalData(String user,String pass){
        this.user=user;
        this.pass=pass;
    }
    public PersonalData(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
