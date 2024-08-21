import javax.swing.*;

public class User{
    private String username;
    private int password;

    public void User(String username, int password){
        this.username=username;
        this.password=password;
    }
    public int getPassword(){
        return password;
    }
    public void setPassword(int password){
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String user){

        this.username=username;
    }
}
