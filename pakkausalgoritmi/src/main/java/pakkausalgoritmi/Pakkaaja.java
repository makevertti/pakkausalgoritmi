
package pakkausalgoritmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import pakkausalgoritmi.tietorakenteet.Solmu;

/**
 * Tiedostojen pakkaamisesta vastuussa oleva luokka.
 */
public class Pakkaaja {
    private File tiedosto;
    private Merkkilaskuri merkkilaskuri;
    private int[] merkkimaarat;
    private BufferedReader lukija;
    private Bittikirjoitin bittikirjoitin;
    private String[] koodit;
    
    /**
     * 
     * @param pakattavaTiedosto Tiedosto mikä halutaan pakata
     * @param kirjoitettavaTiedosto Tiedosto mihin pakattu data kirjoitetaan
     */
    public Pakkaaja(File pakattavaTiedosto, File kirjoitettavaTiedosto) {
        this.tiedosto = pakattavaTiedosto;
        this.merkkilaskuri = new Merkkilaskuri();
        this.bittikirjoitin = new Bittikirjoitin(kirjoitettavaTiedosto);
        this.koodit = new String[257];
    }

    /**
     * Pakkausmetodi
     */
    public void pakkaa() {
        try {
            this.lukija = new BufferedReader(new InputStreamReader(new FileInputStream(this.tiedosto)));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
        }
        merkkimaarat = merkkilaskuri.laskeMerkkienMaarat(this.tiedosto);
        Solmu puu = rakennaMerkkipuu(); 
        muodostaKoodit(koodit, puu, "");     
        kirjoitaPuuTiedostoon(puu);
        kirjoitaPakattuTiedosto();
    }

    private Solmu rakennaMerkkipuu() {
        PriorityQueue<Solmu> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < merkkimaarat.length; i++) {
            if (merkkimaarat[i] != 0) {
                priorityQueue.add(new Solmu(i, merkkimaarat[i], null, null));
            }
        }
        
        priorityQueue.add(new Solmu(256, 1, null, null));

        while (priorityQueue.size() > 1) {
            Solmu vasen = priorityQueue.poll();
            Solmu oikea = priorityQueue.poll();

            priorityQueue.add(new Solmu(-1, vasen.getMaara() + oikea.getMaara(), vasen, oikea));
        }

        return priorityQueue.poll();
    }

    private void muodostaKoodit(String[] koodit, Solmu solmu, String koodi) {
        if (!solmu.onLehti()) {
            muodostaKoodit(koodit, solmu.getVasen(), koodi + '0'); 
            muodostaKoodit(koodit, solmu.getOikea(), koodi + '1');               
        } else {
            koodit[solmu.getMerkki()] = koodi;
        }
    }
    
    private void kirjoitaPuuTiedostoon(Solmu puu) {
        if (puu.onLehti()) {
            bittikirjoitin.kirjoitaBitti(1);
            String merkkiBitteina = Integer.toBinaryString(puu.getMerkki());
            
            //Tasaa solmujen bittiesityksen saman pituisiksi
            for (int i = 9 - merkkiBitteina.length(); i > 0; i--) {
                bittikirjoitin.kirjoitaBitti(0);
            }
            
            for (int i = 0; i < merkkiBitteina.length(); i++) {
                if (merkkiBitteina.charAt(i) == '1') {
                    bittikirjoitin.kirjoitaBitti(1);
                } else {
                    bittikirjoitin.kirjoitaBitti(0);
                }
            }
        } else {
            bittikirjoitin.kirjoitaBitti(0);
            kirjoitaPuuTiedostoon(puu.getVasen());
            kirjoitaPuuTiedostoon(puu.getOikea());
        }
    }

    private void kirjoitaPakattuTiedosto() {
        while(true) {
            int merkki = 0;
            try {
                merkki = lukija.read();
            } catch (IOException ex) {
                System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
            }
            if (merkki == -1) {
                break;
            }
            String merkkikoodi = koodit[merkki];
            for (int i = 0; i < merkkikoodi.length(); i++) {
                if (merkkikoodi.charAt(i) == '1') {
                    bittikirjoitin.kirjoitaBitti(1);
                } else {
                    bittikirjoitin.kirjoitaBitti(0);
                }
            }
        }
        String loppumerkki = koodit[256];
        for (int i = 0; i < loppumerkki.length(); i++) {
            if (loppumerkki.charAt(i) == '1') {
                bittikirjoitin.kirjoitaBitti(1);
            } else {
                bittikirjoitin.kirjoitaBitti(0);
            }
        }
        bittikirjoitin.sulje();
    }
}