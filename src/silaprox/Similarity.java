/*N
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.NounSynset;

public class Similarity {

    public final static int maxDeep = 18;

    //Utilidades
    public static boolean sameNounSynset(Termino p1, Termino p2) {

        return p1.getNounSynset().equals(p2.getNounSynset());

    }

    public static Termino findCommonTerm(Termino p1, Termino p2) {
        int k = 0, l = 0;
        boolean found = false;
        Termino resultado = new Termino();

        while (k < p1.getHypernyms().size() && found == false) {
            l = 0;
            while (l < p2.getHypernyms().size() && found == false) {
                if (p1.getHypernyms().get(k).equals(p2.getHypernyms().get(l))) {
                    resultado.setNounSynset(p1.getHypernyms().get(k));
                    found = true;
                }
                l++;
            }
            k++;
        }

        return resultado;
    }

    public static int distanceToCommonTerm(Termino p1, Termino comun) {

        int i = 0;

        while (!p1.getHypernyms().get(i).equals(comun.getNounSynset())) {

            i++;

        }
        if (i != 0) {
            return i;
        } else {
            return ++i;
        }
    }

    public static float length(Termino p1, Termino p2) {

        Termino common = new Termino();
        int a, b;

        common = findCommonTerm(p1, p2);

        a = distanceToCommonTerm(p1, common);
        b = distanceToCommonTerm(p2, common);

        return (float) (a + b + 1);
    }

    public static int CommonTermDeep(Termino p1, Termino p2) {

        int k = Deep(p1);

        int f = distanceToCommonTerm(p1, findCommonTerm(p1, p2));

        return (k + 1) - f;
    }

    public static int Deep(Termino p1) {

        int k = 0;

        for (NounSynset hypernym : p1.getHypernyms()) {

            k++;
        }

        return ++k;

    }

    // Medidas
    public static float pathLenght(Termino p1, Termino p2) {

        if (Similarity.sameNounSynset(p1, p2)) {

            return 1;

        } else {

            Termino common = new Termino();
            int a, b;

            common = findCommonTerm(p1, p2);

            a = distanceToCommonTerm(p1, common);
            b = distanceToCommonTerm(p2, common);

            return (float) 1 / (a + b + 1);
        }

    }

    public static float WuAndPalmer(Termino p1, Termino p2) {

        if (Similarity.sameNounSynset(p1, p2)) {

            return 1;

        } else {

            float resultado;

            resultado = (2 * Similarity.CommonTermDeep(p1, p2)) / (Similarity.length(p1, p2) + 2 * Similarity.CommonTermDeep(p1, p2));

            return (float) resultado;
        }
    }

    public static float WuAndPalmer2(Termino p1, Termino p2) {

        if (Similarity.sameNounSynset(p1, p2)) {

            return 1;

        } else {
            
            float resultado;

            float a = Similarity.Deep(p1);
            float b = Similarity.Deep(p2);

            resultado = (2 * Similarity.CommonTermDeep(p1, p2)) / (a + b);

            return (float) resultado;
        }
    }

}
