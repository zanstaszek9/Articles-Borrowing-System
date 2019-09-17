package sample;

public class Book extends Article{

    private String editor;
    private String publicationPlace;
    private String publisher;

    public Book(){};

    public Book(String title, String author, int yearOfPublication, int pageNumber, String editor, String publicationPlace, String publisher) {
        super(title, author, yearOfPublication, pageNumber);
        this.editor = editor;
        this.publicationPlace = publicationPlace;
        this.publisher = publisher;
    }

    public Book(String title, String author, String yearOfPublication, String pageNumber, String ID, boolean available, String editor, String publicationPlace, String publisher) {
        super(title, author, yearOfPublication, pageNumber, ID, available);
        this.editor = editor;
        this.publicationPlace = publicationPlace;
        this.publisher = publisher;
    }

    public String getEditor() {
        return editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }
    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public final String getExtras(){
        return "Editor: " + this.editor +
                ";  Publisher Place: " + this.publicationPlace +
                ";  Publisher: " + this.publisher +";";
    }

    @Override
    public String toString() {
        return ID + "\t\tBook \n" +
                "Title: \t" + title + " \n" +
                "Author:\t" + author + " \n" +
                "Year: \t" + yearOfPublication + " \n" +
                "Pages:\t" + pageNumber + "\n" +
                "Editor:\t" + editor + "\n" +
                "Pub. Pl.:\t" + publicationPlace + "\n" +
                "Publisher:\t" + publisher;
    }

    public String[] toStringArray(){
        return new String[] {title, author,
                Integer.toString(yearOfPublication), Integer.toString(pageNumber), Boolean.toString(available),
                editor, publicationPlace, publisher};
    }

    public static String [] getFields(){
        return new String[] {"ID", "title", "author",
                "yearOfPublication", "pageNumber", "available",
                "editor", "publicationPlace", "publisher"};
    }

}
