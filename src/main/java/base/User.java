package base;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    public static String username;
    private transient String id;

    public static void setUsername(String username) {
        User.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
