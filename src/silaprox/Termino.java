/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Termino {

    WordNetDatabase database = WordNetDatabase.getFileInstance();

    private String word;
    private NounSynset nounSynset;
    private NounSynset commonTerm;
    private NounSynset[] currentHypernym;
    private ArrayList<NounSynset> hypernyms = new ArrayList<>();

    // Setters    
    public void setCurrentHypernym(NounSynset[] currentHypernym) {
        this.currentHypernym = currentHypernym;
    }

    public void setDatabase(WordNetDatabase database) {
        this.database = database;
    }

    public void setNounSynset(NounSynset nounSynset) {
        this.nounSynset = nounSynset;
    }

    public void setCommonTerm(NounSynset commonTerm) {
        this.commonTerm = commonTerm;
    }

    // Getters
    public ArrayList<NounSynset> getHypernyms() {
        return hypernyms;
    }

    public WordNetDatabase getDatabase() {
        return database;
    }

    public NounSynset getNounSynset() {
        return nounSynset;
    }

    public NounSynset getCommonTerm() {
        return commonTerm;
    }

    public NounSynset[] getCurrentHypernym() {
        return currentHypernym;
    }

    // Metodos Propios
    public void setTermino() {

        this.word = JOptionPane.showInputDialog("Introduzca una palabra");
        Synset[] synsets = database.getSynsets(this.word, SynsetType.NOUN);
        nounSynset = (NounSynset) (synsets[0]);

    }
    
    public void getTermino(){
        
        System.out.println(this.word);
        
    }

    public void calculateHypernym() {

        String aux;

        this.currentHypernym = nounSynset.getHypernyms();

        do {

            aux = this.currentHypernym[0].toString();                                     // Guardamos en un String el hiperonimo

            this.hypernyms.add(currentHypernym[0]);

            currentHypernym = currentHypernym[0].getHypernyms();                            // Cogemos el primer hiperonimo del termino actual

        } while (!aux.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)"));

    }

    public void findCommonTermWith(Termino p2) {
        int k = 0, l = 0;
        boolean found = false;

        while (k < this.hypernyms.size() && found == false) {
            l = 0;
            while (l < p2.getHypernyms().size() && found == false) {
                if (this.hypernyms.get(k).equals(p2.getHypernyms().get(l))) {
                    this.commonTerm = this.getHypernyms().get(k);
                    p2.setCommonTerm(commonTerm);
                    found = true;
                }
                l++;
            }
            k++;
        }

    }

    public int distanceToCommonTerm() {

        int i = 0;

        while (!this.hypernyms.get(i).equals(this.commonTerm)) {

            i++;

        }
        return i;
    }
    
    public void showHypernyms(){
        
        for(int i = 0; i < this.hypernyms.size(); i++){
            System.out.println(this.hypernyms.get(i));
        }
        System.out.println("\n");
    }
}
