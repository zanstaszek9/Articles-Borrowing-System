package sample;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.HashSet;

public class Researcher extends User implements Serializable  {
    private static int idCounter = 1;
    private int ID = -1;
    private String firstname ="";
    private String surname ="";
    private String[] address = {"", "", ""};  // Street, Town, Postcode
    private String phoneNum = "";
    private String email = "";
    private HashSet<Article> borrowed = new HashSet<Article> (6, 0.8f);
    // Using HashSet to avoid duplicates and order of borrowing is not important
    private String username = "";
    private String password = "";

    public Researcher() {}
    public Researcher(String firstname, String surname, String street, String town, String postcode, String phoneNum, String email, String ID) {
        // Password is the same as the username
        this.firstname = firstname;
        this.surname = surname;
        setAddress(street, town, postcode);
        this.phoneNum = phoneNum;
        this.email = email;

        if(Integer.parseInt(ID) == -1)
            this.ID = idCounter++;
        else
            this.ID = Integer.parseInt(ID);
        setUsername(firstname, surname);
        this.password = username;
    }


    public static void setIdCounter(int idCounter) {   Researcher.idCounter = idCounter;    }

    public int getID () {   return ID;  }
    public static int getIDCounter() {  return idCounter;   }

    public void setID(int ID) {
        this.ID = ID;
    }

    private void setUsername(String firstname, String surname){
        // Standard username is first letter of name,
        // whole surname and record ID number like "szan2"
        // If any of needed is empty, default{ID} will be set
        if (firstname.isEmpty() || surname.isEmpty())
            this.username = "default" + ID;
        else
            this.username = Character.toString(firstname.charAt(0)).toLowerCase() + surname.toLowerCase() + ID;
    }
    public String getUsername() {   return this.username;   }
    public String getPassword() {
        return this.password;
    }

    public String getFirstname() {  return firstname;   }
    public void setFirstname(String firstname) {    this.firstname = firstname; }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {    this.surname = surname; }

    public String getFullName() {   return firstname + " " + surname; }


    public String getAddress() {
        return String.join(", ", address);
    }   // Join makes that coma ', ' appears only between values, so we won't get it at very end of string
    public String getStreet(){
        return address[0];
    }
    public String getTown(){
        return address[1];
    }
    public String getPostcode(){
        return address[2];
    }
    public void setAddress(String[] address) {
        this.address = address;
    }
    public void setAddress(String street, String town, String postcode) {
        this.address[0] = street;
        this.address[1] = town;
        this.address[2] = postcode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean borrowArticle(Article a) {
        return borrowed.add(a);
    }
    public String getBorrowed(){
        if(borrowed.isEmpty())
            return "No articles";
        else
            return borrowed.stream().map(b -> b.getTitle() + ", " + b.getAuthor()).collect(Collectors.joining("\n"));
        // With that stream, we get nice String of concatenated important values
    }
    public HashSet<Article> getBorrowedList(){
        return borrowed;
    }

    public void returnArticle(Article a) {
        if (borrowed.contains(a))
            borrowed.remove(a);
    }

    @Override
    public String toString() {
        return  firstname + " " + surname + ", \n" +
                getStreet() + ", \n" +
                getTown() +", " + getPostcode() + ", \n" +
                phoneNum + ", \n" +
                email + ", \n" +
                getBorrowed() +", \n";
    }
/*
    @Override
    public String toString() {
        return "Researcher{" +
                "ID=" + ID +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + Arrays.toString(address) +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", borrowed=" + borrowed +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
*/

    public static String [] getFields(){
        return new String[] {
                "ID", "firstname", "surname",
                "street", "town", "postcode",
                "phoneNum", "email", "borrowed"
        };
    }
}
