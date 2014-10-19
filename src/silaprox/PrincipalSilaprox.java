/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
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
        System.setProperty("wordnet.database.dir", "/usr/local/WordNet-3.0/dict/");
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        
        VerbSynset nounSynset;
        VerbSynset[] hyponyms; 
       
        
        Synset[] synsets = database.getSynsets("shout", SynsetType.VERB);
        for (int i = 0; i < synsets.length; i++) {
            nounSynset = (VerbSynset) (synsets[i]);
            hyponyms = nounSynset.getTroponyms();
            System.out.println(nounSynset.getWordForms()[0]
                    + ": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
        }

    }

}
