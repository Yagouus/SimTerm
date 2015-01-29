package silaprox;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;

public class Termino {

    WordNetDatabase database = WordNetDatabase.getFileInstance();
    private String word;
    private NounSynset nounSynset;
    private ArrayList<ArrayList<NounSynset>> hypernyms = new ArrayList<>();

    //CONSTRUCTOR
    public Termino(NounSynset nounSynset) {
        this.setNounSynset(nounSynset);
    }

    public Termino() {

    }

    //SETTERS
    public void setDatabase(WordNetDatabase database) {
        this.database = database;
    }

    public void setNounSynset(NounSynset nounSynset) {
        this.nounSynset = nounSynset;
        this.word = nounSynset.getWordForms()[0];
    }

    //GETTERS
    public ArrayList<ArrayList<NounSynset>> getHypernyms() {
        return hypernyms;
    }

    public WordNetDatabase getDatabase() {
        return database;
    }

    public NounSynset getNounSynset() {
        return nounSynset;
    }

    //METODOS PROPIOS
    public void setTermino(String termino) {

        this.word = termino;
        Synset[] synsets = database.getSynsets(this.word, SynsetType.NOUN);
        nounSynset = (NounSynset) (synsets[0]);

    }

    public String getTermino() {

        return (this.word);

    }

    public void calculateHypernym() {

        this.hiperonimos(nounSynset, 0);

        /*String aux = null;
         int i = 0;

         this.hypernyms.add(nounSynset);
         this.currentHypernym = nounSynset.getHypernyms();

         do {

         aux = this.currentHypernym[0].toString();                                     // Guardamos en un String el hiperonimo

         this.hypernyms.add(currentHypernym[0]);

         currentHypernym = currentHypernym[0].getHypernyms();                            // Cogemos el primer hiperonimo del termino actual

         i++;

         } while (!aux.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)"));
         */
    }

    public String showHypernyms() {

        String a = "";

        for (int i = 0; i < this.hypernyms.size(); i++) {
            a += "\nTermino: ";
            a += i + "\n";
            for (int j = 0; j < this.hypernyms.get(i).size(); j++) {
                a += (this.hypernyms.get(i).get(j));
                a += "\n";
            }
        }

        a += "\n";

        return a;

    }

    /*public int getShortestDeep(){
        
     int max = 0;
        
     for(int i = 0; i < this.hypernyms.size(); i++){
            
     if(this.hypernyms.get(i).size() > max)
     max = this.hypernyms.get(i).size();   
            
     }
        
     return max;
        
     }*/
    @Override
    public String toString() {
        return this.word; //To change body of generated methods, choose Tools | Templates.
    }

    private void hiperonimos(NounSynset t1, int n) {

        NounSynset[] currentHypernym;
        currentHypernym = new NounSynset[1];
        String aux = null;
        int num = 0;
        int i = 0;

        if (this.hypernyms.isEmpty()) {

            this.hypernyms.add(new ArrayList<NounSynset>());

        } /*else {

         this.hypernyms.add((ArrayList<NounSynset>) this.hypernyms.get(num).clone());
         this.hypernyms.get(0).add(t1);

         }*/

        this.hypernyms.get(n).add(t1);
        aux = t1.toString();
        currentHypernym = t1.getHypernyms();

        if (!aux.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)")) {

            do {
                int numo = num;
                aux = currentHypernym[0].toString();                                     // Guardamos en un String el hiperonimo

                if (currentHypernym.length > 1) {
      
                    for (int j = 1; j < currentHypernym.length; j++) {

                        this.hypernyms.add((ArrayList<NounSynset>) this.hypernyms.get(num).clone());
                        num++;
                        hiperonimos(currentHypernym[j], num);

                    }

                    this.hypernyms.get(numo).add(currentHypernym[0]);

                } else {

                    this.hypernyms.get(n).add(currentHypernym[0]);

                }

                if (!aux.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)")) {

                    currentHypernym = currentHypernym[0].getHypernyms();

                }

                i++;

            } while (!aux.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)"));

        }

    }

}
