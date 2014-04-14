/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dictionnaire d'abreviation. A une abreviation correspond N significations
 * @author Remynoschka
 */
public class Dictionnaire {
    private HashMap<String, List<String>> dico;
    public static final Dictionnaire INSTANCE = new Dictionnaire();
    private final String DICO_PAHT = "./data/dictionnaire.dic";
    private File dicoFile;
    
    private Dictionnaire(){
        dico = new HashMap<>();
        dicoFile = new File(DICO_PAHT);
        if(!dicoFile.exists()){
            try {
                dicoFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // lit le fichier dictionnaire
            try(BufferedReader reader = new BufferedReader(new FileReader(dicoFile))){
                String line = "";
                while ((line = reader.readLine()) != null){
                    String[] parse1 = line.split("=");
                    addAbreviation(parse1[0]);
                    for (String val : parse1[1].split(":")){
                        addEntry(parse1[0], val);
                    }
                }                              
            } catch (IOException ex) {
                Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void setDictionnaire(HashMap<String, List<String>> dico){
        this.dico = dico;
    }
    
    public HashMap<String, List<String>> getDictionnaire(){
        return dico;
    }
    
    /**
     * Ajoute une abreviation au dictionnaire
     * @param abrev : l'abreviation a ajouter
     * @return true si l'abreviation n'existe pas deja
     */
    public boolean addAbreviation(String abrev){
        if (!dico.containsKey(abrev)){
            dico.put(abrev, new ArrayList<String>());
            return true;
        }
        return false;
    }
    
    public void removeAbreviation(String abrev){
        dico.remove(abrev);
    }
    
    /**
     * Ajoute une signification a l'abreviation
     * @param abrev : l'abreviation
     * @param entry : l'entree correspondante
     * @return true si l'entree n'existe pas deja pour cette abreviation
     */
    public boolean addEntry(String abrev, String entry){
        if (!dico.get(abrev).contains(entry)){
            ((List)dico.get(abrev)).add(entry);
            return true;
        }
        
        return false;
    }
    
    public void removeEntry(String abrev, String entry){
        if (dico.get(abrev).contains(entry)){
            ((List)dico.get(abrev)).remove(entry);            
        }
    }
    
    /**
     * Retourne toute les abreviations dans un dictionnaire
     * @return toute les abreviations dans un dictionnaire
     */
    public Set<String> getAbreviations(){
        return dico.keySet();
    }
    
    /**
     * Retourne la liste des differentes significations d'une abreviation
     * @param abrev : l'abreviation dont on veut connaitre les significations
     * @return la liste des differentes significations d'une abreviation
     */
    public List<String> getSignifications(String abrev){
        return dico.get(abrev);
    }
    
    /**
     * Enregistre le dictionnaire
     */
    public void save(){
        try(FileOutputStream fos = new FileOutputStream(dicoFile)){
            FileChannel fc = fos.getChannel();
            for (String key : dico.keySet()){
                String line = key+"=";
                for (String val : dico.get(key)){
                    line+=val+":";
                }
                line = line.substring(0, line.length()-1);
                line += System.getProperty("line.separator");
                ByteBuffer buf = ByteBuffer.wrap(line.getBytes());                
                fc.write(buf);
            }
        } catch (IOException ex) {
            Logger.getLogger(Dictionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
