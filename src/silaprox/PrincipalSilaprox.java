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

/**
 *
 * @author yagouus
 */
public class PrincipalSilaprox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("wordnet.database.dir", "/usr/local/WordNet-3.0/dict/"); // Establecemos la ruta de la base de datos de Wordnet 
        WordNetDatabase database = WordNetDatabase.getFileInstance();

        NounSynset nounSynset;
        NounSynset[] hypernyms;
        String word;

        Synset[] synsets = database.getSynsets("dog", SynsetType.NOUN);         // Palabra a buscar        

        nounSynset = (NounSynset) (synsets[0]);                                 // Elegimos el synset de la palabra que queremos usar
        System.out.println(nounSynset.getWordForms()[0]);                       // Mostramos la palabra a buscar
        
        hypernyms = nounSynset.getHypernyms();                                  // Leemos el primer hiperonimo de la primera glosa

        do {

            word = hypernyms[0].toString();                                     // Guardamos en un String el hiperonimo

            System.out.println(hypernyms[0]);
            hypernyms = hypernyms[0].getHypernyms();                            // Cogemos el primer hiperonimo del termino actual

        } while (!word.equals("Noun@1740[entity] - that which is perceived or known or inferred to have its own distinct existence (living or nonliving)"));
    }

}
