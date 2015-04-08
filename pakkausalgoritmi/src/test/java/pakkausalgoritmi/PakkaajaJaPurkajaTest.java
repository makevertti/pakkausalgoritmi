
package pakkausalgoritmi;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PakkaajaJaPurkajaTest {
    File pakattavaTiedosto;
    File pakattuTiedosto;
    Pakkaaja pakkaaja;
    Purkaja purkaja;
    File purettuTiedosto;

    @Before
    public void setUp() {
        this.pakattavaTiedosto = new File("src/main/resources/pakkausalgoritmi/testi1.txt");
        this.pakattuTiedosto = new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto");
        this.purettuTiedosto = new File("src/main/resources/pakkausalgoritmi/purettu.txt");
        this.pakkaaja = new Pakkaaja(pakattavaTiedosto, pakattuTiedosto);
        this.purkaja = new Purkaja(pakattuTiedosto, purettuTiedosto);
    }

    @Test
    public void pakattuTiedostoOnAlkuperaistaPienempi() {
        pakkaaja.pakkaa();

        assertTrue(pakattuTiedosto.length() < pakattavaTiedosto.length());
    }

    @Test
    public void purkaminenToimii() {
        pakkaaja.pakkaa();
        purkaja.pura();

        byte[] alkuperainen = new byte[0];
        byte[] purettu = new byte[0];
        try {
            alkuperainen = Files.readAllBytes(Paths.get(pakattavaTiedosto.getPath()));
            purettu = Files.readAllBytes(Paths.get(purettuTiedosto.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(Arrays.equals(alkuperainen, purettu));
    }
}
