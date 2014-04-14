/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Dictionnaire d'abreviation. A une abreviation correspond N significations
 * @author Remynoschka
 */
public class Dictionnaire {
    private HashMap<String, List<String>> dico;
    public static final Dictionnaire INSTANCE = new Dictionnaire();
    
    private Dictionnaire(){
        dico = new HashMap<>();
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
}
