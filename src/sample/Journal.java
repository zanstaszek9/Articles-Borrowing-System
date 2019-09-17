package sample;

import java.io.Serializable;


public class Journal extends Article  implements Serializable {

    private String titleOfJournal ="";
    private int volumeNumber = 0;
    private int partNumber = 0;


    public Journal(){}
    public Journal(String title, String author, int yearOfPublication, int pageNumber, String titleOfJournal, int volumeNumber, int partNumber) {
        super(title, author, yearOfPublication, pageNumber);
        this.titleOfJournal = titleOfJournal;
        this.volumeNumber = volumeNumber;
        this.partNumber = partNumber;
    }
    public Journal(String title, String author, String yearOfPublication, String pageNumber, String ID, boolean available, String titleOfJournal, String volumeNumber, String partNumber) {
        super(title, author, yearOfPublication, pageNumber, ID, available);
        this.titleOfJournal = titleOfJournal;
        this.volumeNumber = Integer.parseInt(volumeNumber);
        this.partNumber = Integer.parseInt(partNumber);
    }

    public String getTitleOfJournal() {
        return titleOfJournal;
    }
    public void setTitleOfJournal(String titleOfJournal) {
        this.titleOfJournal = titleOfJournal;
    }

    public int getVolumeNum() {
        return volumeNumber;
    }
    public void setVolumeNum(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public int getPartNum() {
        return partNumber;
    }
    public void setPartNum(int partNumber) {
        this.partNumber = partNumber;
    }

    @Override
    public final String getExtras(){
        return "Title of Journal: " + this.titleOfJournal +
                ";  Volume: " + this.volumeNumber +
                ";  Part: " + this.partNumber +";";
    }


    @Override
    public String toString() {
        return ID + "\t\tJournal \n" +
                "Title: \t" + title + " \n" +
                "Author:\t" + author + " \n" +
                "Year: \t" + yearOfPublication + " \n" +
                "Pages: \t" + pageNumber + "\n" +
                "Journal:\t" + titleOfJournal + "\n" +
                "Volume:\t" + volumeNumber + "\n" +
                "Part: \t" + partNumber;
    }

    public String[] toStringArray(){
        return new String[] {title, author,
                Integer.toString(yearOfPublication), Integer.toString(pageNumber), Boolean.toString(available),
                titleOfJournal, Integer.toString(volumeNumber), Integer.toString(partNumber)};
    }


    public static String [] getFields(){
        return new String[] {"ID", "title", "author",
                "yearOfPublication", "pageNumber", "available",
                "titleOfJournal", "volumeNumber", "partNumber"};
    }
}
