
package pakkausalgoritmi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bittilukija {
    private BufferedInputStream lukija;
    private int bittipuskuri;
    private int bittejaPuskurissa;
    
    public Bittilukija(File tiedosto) {
        try {
            this.lukija = new BufferedInputStream(new FileInputStream(tiedosto));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
        }
        this.bittipuskuri = 0;
        this.bittejaPuskurissa = 0;
    }
    
    public int lueBitti() {
        if (bittejaPuskurissa == 0) {
            lueTavu();
        }
        if (bittipuskuri == -1) {
            return -1;
        }
        bittejaPuskurissa--;
        return (bittipuskuri >>> bittejaPuskurissa) & 1;
    }
    
    private void lueTavu() {
        try {
            bittipuskuri = lukija.read();
        } catch (IOException ex) {
            System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
        }
        bittejaPuskurissa = 8; 
    }
    
    public void sulje() {
        try {
            lukija.close();
        } catch (IOException ex) {
            System.err.println("Tiedoston lukemisessa tapahtui virhe: " + ex);
        }
    }
}