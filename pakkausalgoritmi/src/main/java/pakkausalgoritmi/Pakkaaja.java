
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
    private File pakattavaTiedosto;
    private Merkkilaskuri merkkilaskuri;
    private int[] merkkimaarat;
    private BufferedReader lukija;
    private BittiKirjoitin bittiKirjoitin;
    private String[] koodit;
    
    /**
     * Parametrina annetaan tiedosto joka halutaan pakata.
     * 
     * @param pakattavaTiedosto
     */
    public Pakkaaja() {
        this.merkkilaskuri = new Merkkilaskuri();
        this.bittiKirjoitin = new BittiKirjoitin(new File("pakattuTiedosto"));
        this.koodit = new String[256];
    }

    /**
     * Pakkausmetodi
     */
    public void pakkaa(File pakattavaTiedosto) {
        this.pakattavaTiedosto = pakattavaTiedosto;
        try {
            this.lukija = new BufferedReader(new InputStreamReader(new FileInputStream(pakattavaTiedosto)));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
        }
        merkkimaarat = merkkilaskuri.laskeMerkkienMaarat(pakattavaTiedosto);
        Solmu puu = rakennaMerkkipuu();
        muodostaKoodit(koodit, (Solmu) puu, "");
        kirjoitaPuuTiedostoon(puu);
        kirjoitaPakattuTiedosto(pakattavaTiedosto);
        bittiKirjoitin.sulje();
    }

    private Solmu rakennaMerkkipuu() {
        PriorityQueue<Solmu> priorityQueue = new PriorityQueue<Solmu>();
        for (int i = 0; i < merkkimaarat.length; i++) {
            if (merkkimaarat[i] != 0) {
                priorityQueue.add(new Solmu(i, merkkimaarat[i], null, null));
            }
        }

        while (priorityQueue.size() > 1) {
            Solmu oikea = priorityQueue.poll();
            Solmu vasen = priorityQueue.poll();

            priorityQueue.add(new Solmu(-1, oikea.getMaara() + vasen.getMaara(), oikea, vasen));
        }

        return priorityQueue.poll();
    }

    private void muodostaKoodit(String[] koodit, Solmu solmu, String koodi) {
        if (!solmu.onLehti()) {
            muodostaKoodit(koodit, solmu.getOikea(), koodi + '1');
            muodostaKoodit(koodit, solmu.getVasen(), koodi + '0');                
        } else {
            koodit[solmu.getMerkki()] = koodi;
        }
    }

    private void kirjoitaPuuTiedostoon(Solmu puu) {
        if (puu.onLehti()) {
            bittiKirjoitin.kirjoitaBitti(1);
            String merkkiBitteina = Integer.toBinaryString(puu.getMerkki());
            for (int i = 0; i < merkkiBitteina.length(); i++) {
                bittiKirjoitin.kirjoitaBitti(merkkiBitteina.charAt(i));
            }
        } else {
            bittiKirjoitin.kirjoitaBitti(0);
            kirjoitaPuuTiedostoon(puu.getVasen());
            kirjoitaPuuTiedostoon(puu.getOikea());
        }
    }

    private void kirjoitaPakattuTiedosto(File tiedosto) {
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
                bittiKirjoitin.kirjoitaBitti(merkkikoodi.charAt(i));
            }
        }
    }
}