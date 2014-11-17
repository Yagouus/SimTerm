/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.WordNetDatabase;


public class PrincipalSilaprox {

    public static void main(String[] args) {
        System.setProperty("wordnet.database.dir", "WordNet-3.0/dict/"); // Establecemos la ruta de la base de datos de Wordnet 
        WordNetDatabase database = WordNetDatabase.getFileInstance();

        Termino pComun = new Termino();
        
        Termino palabra1 = new Termino();
        Termino palabra2 = new Termino();

        palabra1.setTermino();
        palabra2.setTermino();

        palabra1.calculateHypernym();
        palabra2.calculateHypernym();

        pComun = Similarity.findCommonTerm(palabra2, palabra1);
        Similarity.distanceToCommonTerm(palabra1, pComun);
        Similarity.distanceToCommonTerm(palabra2, pComun);
        
        palabra1.getTermino();
        palabra1.showHypernyms();

        palabra2.getTermino();
        palabra2.showHypernyms();

        System.out.println("Common Hypernym:" + pComun.getNounSynset());

        System.out.println("\nDistance to commonTerm:");
        System.out.println("Term 1: " + Similarity.distanceToCommonTerm(palabra1, pComun));
        System.out.println("Term 2: " + Similarity.distanceToCommonTerm(palabra2, pComun));
        
        System.out.println("\nMeasures:");
        System.out.println("PathLength: " + Similarity.pathLenght(palabra1, palabra2));
        System.out.println("Wu & Palmer: " + Similarity.WuAndPalmer(palabra1, palabra2));       
        System.out.println("Wu & Palmer2: " + Similarity.WuAndPalmer2(palabra1, palabra2));
        System.out.println("Media Wu & Palmer: " + (Similarity.WuAndPalmer(palabra1, palabra2) + Similarity.WuAndPalmer2(palabra1, palabra2))/2);
    }

}
