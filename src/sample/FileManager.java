package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {


    public static void saveResearchers(Map<Integer, Researcher> res){
        try {
            FileOutputStream outputFile = new FileOutputStream("researchers.dat");
            ObjectOutputStream researcherObject = new ObjectOutputStream(outputFile);

            for(Map.Entry <Integer, Researcher> r : res.entrySet()) {
                researcherObject.writeObject(r.getValue());
            }
            researcherObject.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Researcher> readResearchers(int id) {
        Map <Integer, Researcher> res = new HashMap<>();
        try {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream("researchers.dat"));
            while (true) {
                Researcher researcherObject = (Researcher) inputFile.readObject();
                if(researcherObject == null)
                    break;
                if (id == -2){
                    System.out.println("FM readResearcher: " + researcherObject.getID() + " " +researcherObject.getFullName() + " "+researcherObject.getUsername());
                    res.put(researcherObject.getID(), researcherObject);

                }
                else if (researcherObject.getID()== id){
                    res.put(researcherObject.getID(), researcherObject);
                    return res;
                }
            }
        } catch (IOException e) {
            // File is read until exception occurs
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int readResearcherID(){
        int lastID = 0;
        try {
            for (HashMap.Entry<Integer, Researcher>
                    i : FileManager.readResearchers(-2).entrySet()) {
                lastID = i.getValue().getID();
            }
        }
        catch (Exception e) {
        }
        return lastID;
    }


    public static void saveArticles(Map<Integer, Article> articles){
        try {
            FileOutputStream outputFile = new FileOutputStream("articles.dat");
            ObjectOutputStream articleObject = new ObjectOutputStream(outputFile);

            for(Map.Entry <Integer, Article> a : articles.entrySet()) {
                System.out.println("inside Articles.dat");
                articleObject.writeObject(a.getValue());
            }
            articleObject.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Article> readArticles(int id) {
        Map <Integer, Article> article = new HashMap<>();
        try {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream("articles.dat"));
            while (true) {
                Article articleObject = (Article) inputFile.readObject();
                if(articleObject == null)
                    break;
                if (id == -2)
                    article.put(articleObject.getID(), articleObject);
                else if (articleObject.getID()== id){
                    article.put(articleObject.getID(), articleObject);
                    return article;
                }
            }
        } catch (IOException e) {
            // File is read until exception occurs
            //System.out.println("Error reading file Articles");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return article;
    }

    public static Map<Integer, Article> readArticles(String title) {
        Map <Integer, Article> article = new HashMap<>();
        try {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream("articles.dat"));
            while (true) {
                Article articleObject = (Article) inputFile.readObject();
                if(articleObject == null)
                    break;
                if (articleObject.getTitle().contains(title)){
                    article.put(articleObject.getID(), articleObject);
                }
            }
        } catch (IOException e) {
            // File is read until exception occurs
            //System.out.println("Error reading file Articles");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return article;
    }



    public static int readArticleID(){
        int lastID = 0;
        try {
            for (HashMap.Entry<Integer, Article> i :
                    FileManager.readArticles(-2).entrySet())
                lastID = i.getValue().getID();
        }
        catch (Exception e) {}
        return lastID;
    }
}
