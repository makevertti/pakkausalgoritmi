
package pakkausalgoritmi.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTest {
    private Solmu solmu1;
    private Solmu solmu2;
    private Solmu solmu3;
    
    
    @Before
    public void setUp() {
        solmu1 = new Solmu(125, 25, null, null);
        solmu2 = new Solmu(181, 2, null, null);
        solmu3 = new Solmu(-1, 27, solmu1, solmu2);
    }
    
    @Test
    public void solmuLuodaanOikein1() {
        assertEquals(125, solmu1.getMerkki());
        assertEquals(25, solmu1.getMaara());
        assertEquals(null, solmu1.getOikea());
        assertEquals(null, solmu1.getVasen());
    }
    
    @Test
    public void solmuLuodaanOikein2() {
        assertEquals(-1, solmu3.getMerkki());
        assertEquals(27, solmu3.getMaara());
        assertEquals(solmu1, solmu3.getOikea());
        assertEquals(solmu2, solmu3.getVasen());
    }

    @Test
    public void onLehtiToimii1() {
        assertEquals(true, solmu1.onLehti());
    }
    
    @Test
    public void onLehtiToimii2() {
        assertEquals(false, solmu3.onLehti());
    }
    
    @Test
    public void compareToToimii1() {
        assertEquals(true, solmu1.compareTo(solmu2) > 0);
    }
    
    @Test
    public void compareToToimii2() {
        assertEquals(false, solmu2.compareTo(solmu3) > 0);
    }
}
