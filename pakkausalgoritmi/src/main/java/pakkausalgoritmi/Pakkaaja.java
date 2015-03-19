
package pakkausalgoritmi;

import java.io.File;
import java.util.PriorityQueue;
import pakkausalgoritmi.tietorakenteet.Solmu;

public class Pakkaaja {
    private Merkkilaskuri merkkilaskuri;
    private int[] merkkimaarat;
    
    public Pakkaaja() {
        merkkilaskuri = new Merkkilaskuri();
    }
    
    public void pakkaa(File tiedosto) {
        merkkimaarat = merkkilaskuri.laskeMerkkienMaarat(tiedosto);
        rakennaMerkkipuu();
    }
    
    private void rakennaMerkkipuu() {
        PriorityQueue<Solmu> priorityQueue = new PriorityQueue<Solmu>();
        for (int i = 0; i < merkkimaarat.length; i++) {
            if(merkkimaarat[i] != 0) {
                
            }            
        }
    }
}
