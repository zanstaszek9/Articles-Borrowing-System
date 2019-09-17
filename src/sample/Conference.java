package sample;

public class Conference extends Article {

    private String editors = "";
    private String conferenceLocation = "";

    public Conference(){}
    public Conference(String title, String author, int yearOfPublication, int pageNumber, String editors, String conferenceLocation) {
        super(title, author, yearOfPublication, pageNumber);
        this.editors = editors;
        this.conferenceLocation = conferenceLocation;
    }
    public Conference(String title, String author, String yearOfPublication, String pageNumber, String ID, boolean available, String editors, String conferenceLocation) {
        super(title, author, yearOfPublication, pageNumber, ID, available);
        this.editors = editors;
        this.conferenceLocation = conferenceLocation;
    }

    public String getEditors() {
        return editors;
    }
    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getConferenceLocation() {
        return conferenceLocation;
    }
    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }

    @Override
    public final String getExtras(){
        return "Editors: " + this.editors + ";  Location: " + this.conferenceLocation + ";";
    }


    @Override
    public String toString() {
        return ID + "\t\tConference \n" +
                "Title: \t" + title + " \n" +
                "Author:\t" + author + " \n" +
                "Year: \t" + yearOfPublication + " \n" +
                "Pages:\t" + pageNumber + "\n" +
                "Editors:\t" + editors + "\n" +
                "Conf. Loc.:\t" + conferenceLocation;
    }

    public String[] toStringArray(){
        return new String[] {title, author,
                Integer.toString(yearOfPublication), Integer.toString(pageNumber), Boolean.toString(available),
                editors, conferenceLocation};
    }

    public static String [] getFields(){
        return new String[] {"ID", "title", "author",
                "yearOfPublication", "pageNumber", "available",
                "editors", "conferenceLocation"};
    }
}
