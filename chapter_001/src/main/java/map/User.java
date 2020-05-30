package map;

import java.util.Calendar;

public class User {
    private int id;
    private String name;
    private Calendar birthday;

    public User(String name, int id, Calendar birthday) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

}
