package Modele;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe representant un document
 * @author francoisr
 */
public class Document implements Serializable{
    public String author;
    public Calendar date;
    public String title;
    public String[] keywords;
    public String content;
    
    public static final String FILE_EXTENSION = ".notor";
    /**
     * Creer une instance de Document
     * @param author : l'auteur du document
     * @param title : le titre du document
     * @param keywords : les mots cles associes au documents
     */
    public Document(String author, String title, String[] keywords) {
		this.author = author;
		this.title = title; 
                this.keywords = keywords;
		
    }
    
    /**
     * Creer une instance de Document
     * @param author : l'auteur du document
     * @param title : le titre du document
     */
    public Document(String author, String title) {
		this.author = author;
		this.title = title; 
                this.keywords = new String[0];
		
    }
    
    	public void save(String fileName) {
                FileOutputStream fout;
                ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream(fileName + FILE_EXTENSION);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }finally {
                    if(oos  != null)
                        try {
                            oos.close();
                        } catch (IOException e) {
                        }
		} 
	}
    
    
    
    
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
        
        public String[] getKeywords() {
            return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
}
