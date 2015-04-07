
package pakkausalgoritmi.tietorakenteet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KekoTest {
    Keko keko;

    @Before
    public void setUp() {
        keko = new Keko(5);
    }

    @Test
    public void lisaaminenJaHakeminenToimii() {
        Solmu solmu = new Solmu(0, 1, null, null);
        keko.lisaa(solmu);

        assertEquals(solmu, keko.haePienin());
    }

    @Test
    public void kekoToimiiMinimikekona() {
        Solmu solmu1 = new Solmu(0, 1, null, null);
        Solmu solmu2 = new Solmu(0, 2, null, null);
        Solmu solmu3 = new Solmu(0, 3, null, null);

        keko.lisaa(solmu3);
        keko.lisaa(solmu1);
        keko.lisaa(solmu2);

        assertEquals(solmu1, keko.haePienin());
        assertEquals(solmu2, keko.haePienin());
        assertEquals(solmu3, keko.haePienin());
    }

    @Test
    public void TyhjastaKeostaHakeminenPalauttaaNull() {
        assertEquals(null, keko.haePienin());
    }

    @Test
    public void getKokoPalauttaaOikeanArvon() {
        keko.lisaa(new Solmu(0, 0, null, null));
        keko.lisaa(new Solmu(0, 0, null, null));
        keko.lisaa(new Solmu(0, 0, null, null));

        assertEquals(3, keko.getKoko());
    }
}