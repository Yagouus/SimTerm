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

public class PrincipalSilaprox {

    public static void main(String[] args) {
        System.setProperty("wordnet.database.dir", "/usr/local/WordNet-3.0/dict/"); // Establecemos la ruta de la base de datos de Wordnet 
        WordNetDatabase database = WordNetDatabase.getFileInstance();

        Termino palabra1 = new Termino();
        Termino palabra2 = new Termino();

        palabra1.setTermino();
        palabra2.setTermino();

        palabra1.calculateHypernym();
        palabra2.calculateHypernym();

        palabra1.findCommonTermWith(palabra2);

        palabra1.getTermino();
        palabra1.showHypernyms();

        palabra2.getTermino();
        palabra2.showHypernyms();

        System.out.println("Common Hypernym:" + palabra1.getCommonTerm());

        System.out.println("\nDistance to commonTerm:");
        System.out.println("Term 1: " + palabra1.distanceToCommonTerm());
        System.out.println("Term 2: " + palabra2.distanceToCommonTerm());

    }

}
