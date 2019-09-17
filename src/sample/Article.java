package sample;

import java.io.Serializable;

public abstract class Article implements Serializable {

    //private static int idCounter = 1;
    //private final int ID = idCounter;
    protected static int idCounter = 1;
    protected int ID = -1;
    protected String title = "";
    protected String author = "";
    protected int yearOfPublication = 0;
    protected int pageNumber = 0;
    protected boolean available = true;
    protected Researcher borrower = null;

    public Article(){}
    public Article(String title, String author, int yearOfPublication, int pageNumber){
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.pageNumber = pageNumber;
    }

    public Article(String title, String author, String yearOfPublication, String pageNumber, String ID, boolean available){
        this.title = title;
        this.author = author;
        this.yearOfPublication = Integer.parseInt(yearOfPublication);
        this.pageNumber = Integer.parseInt(pageNumber);
        if(Integer.parseInt(ID) == -1)
            this.ID = idCounter++;
        else
            this.ID = Integer.parseInt(ID);

        this.available = available;
        System.out.println(this.getClass().getSimpleName());
    }

    //public static void setIdCounter(int idCounter) {   Article.idCounter = idCounter;    }

    //public int getID () {   return ID;  }
    //public static int getIdCounter() {  return idCounter;   }

    public int getID () {   return ID;  }
    public static void setIdCounter(int idCounter) {   Article.idCounter = idCounter;    }

    public static int getIDCounter() {  return idCounter;   }
    public void setID(int ID) { this.ID = ID;   }

    public String getTitle() {  return title;    }
    public void setTitle(String title) {    this.title = title; }

    public String getAuthor() { return author;  }
    public void setAuthor(String author) {  this.author = author;   }

    public int getYearOfPublication() { return yearOfPublication;   }
    public void setYearOfPublication(int yearOfPublication) {   this.yearOfPublication = yearOfPublication; }

    public int getPageNumber() {    return pageNumber;  }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber;   }

    public boolean getAvailable() { return available;   }
    public void setAvailable(boolean available) {
        if (available == true)
            borrower = null;
        this.available = available;
    }

    // Distinguishing two getters because of the table listing logic in Article List Controller
    public String getBorrower(){
        if (this.borrower == null)
            return "---";
        else
            return this.borrower.getID() + "  " + this.borrower.getFullName();
    }

    public Researcher getBorrowerObject(){    return this.borrower;    }
    public void setBorrower(Researcher r){
        this.borrower = r;
        available = false;

    }

    public String getType(){
        return this.getClass().getSimpleName();
    }

    public abstract String getExtras(); //  Used for table view

    @Override
    public abstract String toString();

    public static String [] getFields(){
        return new String[] {"ID", "type","title", "author",
                "yearOfPublication", "pageNumber", "available", "borrower", "extras"};
    }
}
