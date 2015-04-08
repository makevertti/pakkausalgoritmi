
package pakkausalgoritmi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class BittikirjoitinJaLukijaTest {
    File testitiedosto;
    Bittikirjoitin bittikirjoitin;
    Bittilukija bittilukija;

    @Before
    public void setUp() {
        try {
            this.testitiedosto = File.createTempFile("testitiedosto", "", new File("/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bittikirjoitin = new Bittikirjoitin(this.testitiedosto);
        this.bittilukija = new Bittilukija(this.testitiedosto);
    }

    @After
    public void poistaTestitiedosto() {
        this.testitiedosto.delete();
    }

    @Test
    public void bittienKirjoitusJaLukuToimii() {
        String bitit = "10101110";
        kirjoitaBitit(bitit);
        String luettu = lueBitit(8);

        assertEquals(bitit, luettu);
    }

    @Test
    public void tiedostonLoppuessaPalautetaanMiinus1() {
        String bitit = "11001011";
        kirjoitaBitit(bitit);
        String luettu = lueBitit(10);

        assertEquals("11001011-1-1", luettu);
    }

    @Test
    public void VajaidenTavujenKasittelyToimii() {
        String bitit = "1101";
        kirjoitaBitit(bitit);
        String luettu = lueBitit(8);

        assertEquals("11010000", luettu);
    }

    private void kirjoitaBitit(String bitit) {
        for (int i = 0; i < bitit.length(); i++) {
            if (bitit.charAt(i) == '0') {
                this.bittikirjoitin.kirjoitaBitti(0);
            } else {
                bittikirjoitin.kirjoitaBitti(1);
            }
        }
        bittikirjoitin.sulje();
    }

    private String lueBitit(int maara) {
        String luettu = "";
        for (int i = 0; i < maara; i++) {
            int bitti = bittilukija.lueBitti();
            luettu += bitti;
        }
        bittilukija.sulje();
        return luettu;
    }
}
