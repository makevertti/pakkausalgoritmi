
package pakkausalgoritmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Merkkilaskuri {
    private int[] merkkimaarat;
    private BufferedReader lukija;
    
    public Merkkilaskuri() {
        merkkimaarat = new int[256];
    }
    
    public int[] laskeMerkkienMaarat(File tiedosto) {
        try {
            lukija = new BufferedReader(new InputStreamReader(new FileInputStream(tiedosto)));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston avaaminen epäonnistui: " + ex);
        }
        
        int merkki = 0;
        while(true) {
            try {
                merkki = lukija.read();
            } catch (IOException ex) {
                System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
            }
            
            if(merkki == -1) {
                break;
            }
            
            merkkimaarat[merkki]++;
        }
        try {
            lukija.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
        return merkkimaarat;
    }
}
