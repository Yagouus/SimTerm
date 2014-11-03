/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.NounSynset;

/**
 *
 * @author yagouus
 */
public class Similarity {

    public Termino findCommonTerm(Termino p1, Termino p2) {
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

    public int distanceToCommonTerm(Termino p1, Termino comun) {

        int i = 0;

        while (!p1.getHypernyms().get(i).equals(comun.getNounSynset())) {

            i++;

        }
        if (i != 0) {
            return ++i;
        } else {
            return i;
        }
    }

    public float pathLenght(Termino p1, Termino p2) {

        Termino common = new Termino();
        int a, b;

        common = findCommonTerm(p1, p2);

        a = distanceToCommonTerm(p1, common);
        b = distanceToCommonTerm(p2, common);

        return (float) 1 / (a + b + 1);

    }

}
