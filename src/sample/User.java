package sample;


public abstract class User {

    protected String username;
    protected String password;

    public String getUsername() {   return this.username;   }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public abstract String toString();
}
