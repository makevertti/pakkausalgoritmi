package pakkausalgoritmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import pakkausalgoritmi.tietorakenteet.Solmu;

/**
 * Tiedostojen purkamisen hoitava luokka
 */
public class Purkaja {
    private File kirjoitettavaTiedosto;
    private Bittilukija bittilukija;
    private BufferedWriter kirjoitin;
    private Solmu alkuperainenPuu;
    
    /**
     * Luo uuden purkajan joka käsittelee annettuja tiedostoja
     *
     * @param purettavaTiedosto     Tiedosto joka halutaan purkaa
     * @param kirjoitettavaTiedosto Tiedosto johon purettu data kirjoitetaan
     */
    public Purkaja(File purettavaTiedosto, File kirjoitettavaTiedosto) {
        this.kirjoitettavaTiedosto = kirjoitettavaTiedosto;
        this.bittilukija = new Bittilukija(purettavaTiedosto);
        try {
            this.kirjoitin = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.kirjoitettavaTiedosto)));
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedoston purkamisessa tapahtui virhe: " + ex);
        }
    }

    /**
     * Purkumetodi
     */
    public void pura() {
        Solmu puu = uudelleenrakennaMerkkipuu();
        this.alkuperainenPuu = puu;
        kirjoitaPurettuTiedosto(puu);
    }

    private Solmu uudelleenrakennaMerkkipuu() {
        if (bittilukija.lueBitti() == 1) {
            int solmunMerkki = 0;
            for (int i = 0; i < 9; i++) {
                solmunMerkki = solmunMerkki << 1 | bittilukija.lueBitti();
            }
            return new Solmu(solmunMerkki, 1, null, null);
        } else {
            Solmu vasen = uudelleenrakennaMerkkipuu();
            Solmu oikea = uudelleenrakennaMerkkipuu();
            return new Solmu(-1, 1, vasen, oikea);
        }
    }

    private void kirjoitaPurettuTiedosto(Solmu puu) {
        Solmu solmu = puu;
        while (true) {
            if (solmu.onLehti()) {
                try {
                    if (solmu.getMerkki() == 256) {
                        break;
                    }
                    kirjoitin.write(solmu.getMerkki());
                } catch (IOException ex) {
                    System.err.println("Tiedoston purkamisessa tapahtui virhe: " + ex);
                }
                solmu = this.alkuperainenPuu;
            } else {
                int bitti = bittilukija.lueBitti();
                if (bitti == -1) {
                    break;
                } else if (bitti == 0) {
                    if (solmu.getVasen() != null) {
                        solmu = solmu.getVasen();
                    }
                } else {
                    if (solmu.getOikea() != null) {
                        solmu = solmu.getOikea();
                    }
                }
            }
        }
        try {
            kirjoitin.close();
            bittilukija.sulje();
        } catch (IOException ex) {
            System.out.println("Tiedoston purkamisessa tapahtui virhe: " + ex);
        }
    }
}