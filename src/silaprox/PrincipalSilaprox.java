/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silaprox;

import edu.smu.tspell.wordnet.WordNetDatabase;
import GUI.Ventana;

public class PrincipalSilaprox {
    

    public static void main(String[] args) {
        System.setProperty("wordnet.database.dir", "WordNet-3.0/dict/"); // Establecemos la ruta de la base de datos de Wordnet 
        WordNetDatabase database = WordNetDatabase.getFileInstance();

        Ventana prueba = new Ventana();
        prueba.setVisible(true);

        
        

        
    }

}
