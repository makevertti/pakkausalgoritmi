
package pakkausalgoritmi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Luokka jonka avulla on mahdollista
 * kirjoittaa tiedostoon bittejä
 */
public class Bittikirjoitin {
    private BufferedOutputStream kirjoitin;
    private int tavu;
    private int tavuunKirjoitettu;

    /**
     * Luo uuden bittikirjoittimen joka kirjoittaa
     * annettuun tiedostoon
     *
     * @param tiedosto Tiedosto johon kirjoitetaan
     */
    public Bittikirjoitin(File tiedosto) {
        try {
            this.kirjoitin = new BufferedOutputStream(new FileOutputStream(tiedosto));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston kirjoittamisessa tapahtui virhe: " + ex);
        }
        this.tavu = 0;
        this.tavuunKirjoitettu = 0;
    }
    
    /**
     * Kirjoittaa parametrina annetun bitin tiedostoon.
     * 
     * @param bitti Kirjoitettava bitti. 0 tai 1
     */
    public void kirjoitaBitti(int bitti) {
        if (bitti != 0 && bitti != 1) {
            throw new IllegalArgumentException("Bitin oltava 0 tai 1");
        }
        tavu = tavu << 1 | bitti;
        tavuunKirjoitettu++;
        if (tavuunKirjoitettu == 8) {
            try {
                kirjoitin.write(tavu);
            } catch (IOException ex) {
                System.err.println("Tiedoston kirjoittamisessa tapahtui virhe: " + ex);
            }
            tavuunKirjoitettu = 0;
        }
    }
    
    /**
     * Sulkee BittiKirjoittimen. Jos kirjoitettujen bittien
     * määrä ei täytä tavua lisätään tarvitta määrä 
     * nollia, jotta tavun kirjoitus onnistuu.
     */
    public void sulje() {
        while (tavuunKirjoitettu != 0) {            
            kirjoitaBitti(0);
        }
        try {
            kirjoitin.close();
        } catch (IOException ex) {
            System.err.println("Tiedoston kirjoittamisessa tapahtui virhe: " + ex);
        }
    }
}