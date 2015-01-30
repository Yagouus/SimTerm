package silaprox;

import edu.smu.tspell.wordnet.NounSynset;
import static java.lang.Math.log;
import java.util.ArrayList;

public class Similarity {

    public final static int maxDepth = 18;
    public Termino t1;
    public Termino t2;
    int maxDepthWordnet = 20;

    //UTILIDADES
    //Calcula si un termino es igual a otro (mismo synset)
    public boolean sameNounSynset(Termino p1, Termino p2) {

        return p1.getNounSynset().equals(p2.getNounSynset());

    }

    //Calcula el hyperonimo comun entre dos terminos
    public Termino findCommonTerm(Termino p1, Termino p2) {
        int k = 0, l = 0;
        boolean found = false;
        ArrayList<Termino> commons = new ArrayList<>();
        Termino LCS;

        //Calculamos todos los terminos comunes existentes
        for (int i = 0; i < p1.getHypernyms().size(); i++) {
            for (int j = 0; j < p2.getHypernyms().size(); j++) {
                while (k < p1.getHypernyms().get(i).size() && found == false) {
                    l = 0;
                    while (l < p2.getHypernyms().get(j).size() && found == false) {
                        if (p1.getHypernyms().get(i).get(k).equals(p2.getHypernyms().get(j).get(l))) {
                            commons.add(new Termino(p1.getHypernyms().get(i).get(k)));
                            found = true;
                        }

                        l++;
                    }
                    k++;
                }
            }
        }

        //Calculamos la profundidad de los terminos
        int d = 0;
        LCS = commons.get(0);
        for (int z = 0; z < commons.size(); z++) {

            if (d < termDepth(p1, commons.get(z))) {
                d = termDepth(p1, commons.get(z));
                LCS = commons.get(z);
            }

        }

        return LCS;
    }

    //Metodo auxiliar para calcular la altura de un termino
    public int termDepth(Termino p1, Termino comun) {

        int k = 1;

        for (int j = 0; j < p1.getHypernyms().size(); j++) {

            k = 1;
            int i = p1.getHypernyms().get(j).size() - 1;

            while (i > 0) {
                NounSynset aux = p1.getHypernyms().get(j).get(i);
                k++;
                i--;
                if (aux.equals(comun.getNounSynset())) {
                    return k;
                }

            }

        }

        return k;

    }

    //Calcula la distancia entre un termino y un hiperonimo (un termino y el Comun)
    public int distanceToCommonTerm(Termino p1, Termino comun) {

        int i = 0, k = 19, j = 0;

        for (i = 0; i < p1.getHypernyms().size(); i++) {
            j = 0;
            while (j < p1.getHypernyms().get(i).size()) {
                NounSynset aux = p1.getHypernyms().get(i).get(j);
                j++;

                if (aux.equals(comun.getNounSynset())) {
                    break;
                }

            }
            if (j == 0) {
                j++;
            }

            if (j < k) {
                k = j;
            }

        }

        return k - 1;

    }

    //Calcula la distancia entre dos terminos
    public float length(Termino p1, Termino p2) {

        Termino common = new Termino();
        int a, b;

        common = findCommonTerm(p1, p2);

        a = distanceToCommonTerm(p1, common);
        b = distanceToCommonTerm(p2, common);

        return (float) (a + b + 1);
    }

    //Calcula la profundidad del hyperonimo de un termino dados estos
    public int CommonTermDepth(Termino p1, Termino p2) {

        int k = 0; //p1.getShortestDepth();

        int f = distanceToCommonTerm(p1, findCommonTerm(p1, p2));

        return (k) - f;
    }

    //Calcula si un termino es hyperonimo de otro
    public boolean TermIsHypernym(Termino p1, Termino p2) {

        for (int i = 0; i < p1.getHypernyms().size(); i++) {

            if (p1.getHypernyms().get(i).equals(p2.getNounSynset())) {
                return true;
            }

        }

        return false;

    }

    //MEDIDAS
    public float pathLenght(Termino p1, Termino p2) {

        Termino common = new Termino();
        int a, b;

        common = findCommonTerm(p1, p2);

        a = distanceToCommonTerm(p1, common);
        b = distanceToCommonTerm(p2, common);

        if (TermIsHypernym(p1, p2)) {
            return (float) 1 / (a + b);
        } else {
            return (float) 1 / (a + b + 1);
        }

    }

    public float WuAndPalmer(Termino p1, Termino p2) {

        float resultado;

        int a = this.distanceToCommonTerm(p1, this.findCommonTerm(p1, p2));
        int b = this.distanceToCommonTerm(p2, this.findCommonTerm(p1, p2));
        int c = this.termDepth(p1, this.findCommonTerm(p1, p2));

        resultado = (float) (2 * c) / ((a + b) + 2 * c);

        return (float) resultado;

    }

    public float leacockChodorow(Termino p1, Termino p2) {
        
        float resultado;

        float a = this.length(p1, p2);
               
        resultado = (float) -log(a / (2*maxDepthWordnet)); 

        return (float) resultado;
    }
}
