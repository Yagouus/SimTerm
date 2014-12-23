package silaprox;

import edu.smu.tspell.wordnet.NounSynset;

public class Similarity {

    public final static int maxDeep = 18;

    //Utilidades
    //Calcula si un termino es igual a otro (mismo synset)
    public static boolean sameNounSynset(Termino p1, Termino p2) {

        return p1.getNounSynset().equals(p2.getNounSynset());

    }

    //Calcula el hyperonimo comun entre dos terminos
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

    //Calcula la distancia entre un termino y un hiperonimo (un termino y el Comun)
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

    //Calcula la distancia entre dos terminos
    public static float length(Termino p1, Termino p2) {

        Termino common = new Termino();
        int a, b;

        common = findCommonTerm(p1, p2);

        a = distanceToCommonTerm(p1, common);
        b = distanceToCommonTerm(p2, common);

        return (float) (a + b + 1);
    }

    //Calcula la profundidad del hyperonimo de un termino dados estos
    public static int CommonTermDeep(Termino p1, Termino p2) {

        int k = 0; //p1.getShortestDeep();

        int f = distanceToCommonTerm(p1, findCommonTerm(p1, p2));

        return (k) - f;
    }

    //Calcula si un termino es hyperonimo de otro
    public static boolean TermIsHypernym(Termino p1, Termino p2) {

        for (int i = 0; i < p1.getHypernyms().size(); i++) {

            if (p1.getHypernyms().get(i).equals(p2.getNounSynset())) {
                return true;
            }

        }

        return false;

    }

    //Calcular si un termino es hiperonimo de otro
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

            if (TermIsHypernym(p1, p2)) {
                return (float) 1 / (a + b);
            } else {
                return (float) 1 / (a + b + 1);
            }
        }

    }

    /*public static float WuAndPalmer(Termino p1, Termino p2) {

     if (Similarity.sameNounSynset(p1, p2)) {

     return 1;

     } else {

     float resultado;

     int a = Similarity.distanceToCommonTerm(p1, Similarity.findCommonTerm(p1, p2));
     int b = Similarity.distanceToCommonTerm(p2, Similarity.findCommonTerm(p1, p2));
     int c = Similarity.CommonTermDeep(p1, p2);

     resultado = (float) (2 * c) / ((a + b) + 2 * c);

     return (float) resultado;
     }
     }
     */
    public static float WuAndPalmer2(Termino p1, Termino p2) {

        if (Similarity.sameNounSynset(p1, p2)) {

            return 1;

        } else {

            float resultado;

            float a = 0; //GETDEEP
            float b = 0; 
            float c = Similarity.CommonTermDeep(p1, p2);

            resultado = (2 * c) / (a + b);

            return (float) resultado;
        }
    }

}
